package org.topnetwork.analysis.block;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
@Slf4j
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

    public boolean loadTableBlock(String address, Long height) {
        TableBlockResult tableBlockResult = topGrpcClient.getTableBlock(address, height);
        if (tableBlockResult.getValue() == null) {
            return false;
        }

        loadTableBlock(tableBlockResult);
        return true;
    }

    @Transactional
    public void loadTableBlock(TableBlockResult tableBlockResult) {
        try {
            //保存tableblock至数据库
            saveTableBlock(tableBlockResult);
            //保存unitblock
            asyncLoadUnitBlock(tableBlockResult);
            //保存transaction
            asyncLoadTransaction(tableBlockResult);
        }catch (DuplicateKeyException e){
            log.info("duplicate tableblock, address={}, height={}, hash={}",
                    tableBlockResult.getValue().getOwner(),
                    tableBlockResult.getValue().getHeight(),
                    tableBlockResult.getValue().getHash());
        }
    }

    /**
     * 保存tableblock到db
     * @param tableBlockResult
     * @return
     */
    private TopTableBlock saveTableBlock(TableBlockResult tableBlockResult) {
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


        //计算unitblock个数
        Body body = tableBlock.getBody();
        int unitblockCount = 0;
        if(body.getTableblock() != null){
            unitblockCount = calculateUnitBlockSize(body.getTableblock());
        }

        Header header = tableBlock.getHeader();

        TopTableBlock topTableBlockDO = new TopTableBlock();
        topTableBlockDO.setOwner(owner);
        topTableBlockDO.setHash(hash);
        topTableBlockDO.setPrevHash(preHash);
        topTableBlockDO.setHeight(height);
        topTableBlockDO.setTimestamp(timestamp);
        topTableBlockDO.setTableId(tableId);
        topTableBlockDO.setShardId(shardId);
        topTableBlockDO.setClusterId(clusterId);
        topTableBlockDO.setZoneId(zoneId);
        topTableBlockDO.setTimerHeight(header.getTimerblock_height());
        topTableBlockDO.setAuditorXip(header.getAuditor_xip());
        topTableBlockDO.setValidator(header.getValidator());
        topTableBlockDO.setValidatorXip(header.getValidator_xip());
        topTableBlockDO.setUnitblockCount(unitblockCount);
        topTableBlockService.save(topTableBlockDO);
        //更新最新高度数据
        topTableBlockService.updateLatestHeight(topTableBlockDO.getOwner(), topTableBlockDO.getHeight());

        return topTableBlockDO;
    }

    /**
     * 同步tableblock中包含的unitblock
     *
     * @param tableBlockResult
     */
    private void asyncLoadUnitBlock(TableBlockResult tableBlockResult) {
        Tableblock tableblock = tableBlockResult.getValue().getBody().getTableblock();
        if (tableblock == null) {
            return;
        }

        Map<String, UnitsBlockMap> unitsBlockMapMap = tableblock.getUnits();
        for (Map.Entry<String, UnitsBlockMap> entry : unitsBlockMapMap.entrySet()) {
            String tableHash = tableBlockResult.getValue().getHash();
            String account = entry.getKey();
            Long height = entry.getValue().getUnit_height();

            unitBlockLoader.asyncSaveNewUnitBlock(tableHash, account, height);
        }
    }

    /**
     * 同步tableblock中包含的transaction
     *
     * @param tableBlockResult
     */
    private void asyncLoadTransaction(TableBlockResult tableBlockResult) {
        Tableblock tableblock = tableBlockResult.getValue().getBody().getTableblock();
        if (tableblock == null) {
            return;
        }

        Map<String, UnitsBlockMap> unitsBlockMapMap = tableblock.getUnits();
        for (Map.Entry<String, UnitsBlockMap> entry : unitsBlockMapMap.entrySet()) {
            Map<String, LightUnitInput> lightUnitInputMap = entry.getValue().getLightunit_input();
            if (lightUnitInputMap == null) {
                continue;
            }
            for (String txHash : lightUnitInputMap.keySet()) {
                transactionLoader.asyncLoadTransaction(txHash);
            }
        }
    }

    private int calculateUnitBlockSize(Tableblock tableblock){
        Map<String, UnitsBlockMap> unitsMap = tableblock.getUnits();
        int unitBlockCount = 0;
        if(unitsMap != null) {
            for (Map.Entry<String, UnitsBlockMap> entry : unitsMap.entrySet()) {
                Map<String, LightUnitInput> unitblockMap = entry.getValue().getLightunit_input();
                if (unitblockMap != null) {
                    unitBlockCount += unitblockMap.size();
                }
            }
        }

        return unitBlockCount;
    }


}
