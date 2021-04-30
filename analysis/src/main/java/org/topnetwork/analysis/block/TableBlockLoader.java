package org.topnetwork.analysis.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.topnetwork.common.entity.TopTableBlock;
import org.topnetwork.common.service.TopTableBlockService;
import org.topnetwork.grpclib.pojo.stream.*;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.util.Map;

/**
 * @author CasonCai
 * @since 2021/4/20 下午6:08
 **/
@Component
public class TableBlockLoader {

    @Autowired
    TopTableBlockService topTableBlockService;

    @Autowired
    UnitBlockLoader unitBlockLoader;

    @Autowired
    TransactionLoader transactionLoader;

    @Autowired
    TopGrpcClient topGrpcClient;

    public boolean loadTableBlock(String address, Long height){
        TableBlockResult tableBlockResult = topGrpcClient.getTableBlock(address, height);
        if(tableBlockResult.getValue() == null){
            return false;
        }

        loadTableBlock(tableBlockResult);

        return true;
    }

    @Transactional
    public void loadTableBlock(TableBlockResult tableBlockResult){
        //保存tableblock至数据库
        saveTableBlock(tableBlockResult);
        //保存unitblock
        asyncLoadUnitBlock(tableBlockResult);
        //保存transaction
        asyncLoadTransaction(tableBlockResult);
    }


    private TopTableBlock saveTableBlock(TableBlockResult tableBlockResult){
        Value tableBlock = tableBlockResult.getValue();
        Long height = tableBlock.getHeight();
        int clusterId = tableBlock.getCluster_id();
        String owner = tableBlock.getOwner();
        String hash = tableBlock.getHash();
        String preHash = tableBlock.getPrev_hash();
        int zoneId = tableBlock.getZone_id();
        int shardId = tableBlock.getShard_id();
        int tableId = tableBlock.getTable_id();
        long timestamp = tableBlock.getTimestamp();

        Body body = tableBlock.getBody();
        Header header = tableBlock.getHeader();

        TopTableBlock topTableBlockDO = new TopTableBlock();
        topTableBlockDO.setOwner(owner);
        topTableBlockDO.setHash(hash);
        topTableBlockDO.setPreHash(preHash);
        topTableBlockDO.setHeight(height);
        topTableBlockDO.setTimestamp(timestamp);
        topTableBlockDO.setTableId(tableId);
        topTableBlockDO.setShardId(shardId);
        topTableBlockDO.setClusterId(clusterId);
        topTableBlockDO.setZoneId(zoneId);
        topTableBlockDO.setTimerHeight(header.getTimerblock_height());
        topTableBlockDO.setAuditorXip(header.getAuditor_xip());
        topTableBlockDO.setValidatorAddress(header.getValidator());
        topTableBlockDO.setValidatorXip(header.getValidator_xip());

        topTableBlockService.save(topTableBlockDO);

        return topTableBlockDO;
    }

    /**
     * 同步tableblock中包含的unitblock
     * @param tableBlockResult
     */
    private void asyncLoadUnitBlock(TableBlockResult tableBlockResult){
        Tableblock tableblock = tableBlockResult.getValue().getBody().getTableblock();
        if(tableblock == null){
            return;
        }


        Map<String, UnitsBlockMap> unitsBlockMapMap = tableblock.getUnits();
        for (Map.Entry<String, UnitsBlockMap> entry : unitsBlockMapMap.entrySet()) {
            String tableHash = tableBlockResult.getValue().getHash();
            String account = entry.getKey();
            Long height = entry.getValue().getUnit_height();

            unitBlockLoader.asyncSaveNewUnitBlock(tableHash,account, height);
        }
    }

    /**
     * 同步tableblock中包含的transaction
     * @param tableBlockResult
     */
    private void asyncLoadTransaction(TableBlockResult tableBlockResult){
        Tableblock tableblock = tableBlockResult.getValue().getBody().getTableblock();
        if(tableblock == null){
            return;
        }

        Map<String, UnitsBlockMap> unitsBlockMapMap = tableblock.getUnits();
        for (Map.Entry<String, UnitsBlockMap> entry : unitsBlockMapMap.entrySet()) {
            Map<String, LightUnitInput> lightUnitInputMap = entry.getValue().getLightunit_input();
            if(lightUnitInputMap == null){
                continue;
            }
            for (String txHash : lightUnitInputMap.keySet()) {
                transactionLoader.asyncLoadTransaction(txHash);
            }
        }
    }

}
