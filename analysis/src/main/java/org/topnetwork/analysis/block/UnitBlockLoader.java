package org.topnetwork.analysis.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.topnetwork.analysis.mq.BlockMessageProducer;
import org.topnetwork.common.entity.TopUnitBlock;
import org.topnetwork.common.enums.UnitBlockType;
import org.topnetwork.common.service.TopAccountService;
import org.topnetwork.common.service.TopUnitBlockService;
import org.topnetwork.grpclib.pojo.unit.*;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

/**
 * @author CasonCai
 * @since 2021/4/20 下午7:25
 **/
@Slf4j
@Component
@RocketMQMessageListener(topic = "${block.analysis.topic.unitblock}",
        consumerGroup = "${block.analysis.consumegroup.unitblock}")
public class UnitBlockLoader implements RocketMQListener<UnitBlockLoader.UnitBlockParam> {

    @Autowired
    TopGrpcClient grpcClient;

    @Autowired
    TopAccountService topAccountService;

    @Autowired
    TopUnitBlockService unitBlockService;

    @Autowired
    BlockMessageProducer blockMessageProducer;

    @Autowired
    RocketMQTemplate rocketMQTemplate;


    @org.springframework.beans.factory.annotation.Value("${block.analysis.topic.unitblock}")
    private String asyncTopic;

    /**
     * 异步保存unitblock
     *
     * @param address
     * @param height
     */
    public void asyncSaveNewUnitBlock(String tableBlockHash,String address, Long height) {
        rocketMQTemplate.syncSend(asyncTopic, new UnitBlockParam(tableBlockHash,address, height));
    }

    @Override
    public void onMessage(UnitBlockParam unitBlockParam) {
        saveNewUnitBlock(unitBlockParam.getTableBlockHash(), unitBlockParam.getAddress(), unitBlockParam.getHeight());
    }

    /**
     *
     * @param addr
     * @param height
     */
    @Transactional
    public void saveNewUnitBlock(String tableBlockHash, String addr, Long height) {

        UnitBlockResult unitBlockResult = grpcClient.getUnitBlock(addr, height);

        saveToDB(tableBlockHash, unitBlockResult);
    }

    private void saveToDB(String tableBlockHash, UnitBlockResult unitBlockResult) {
        UnitBlockValue unitBlockValue = unitBlockResult.getValue();

        UnitBlockType unitBlockType = unitBlockValue.getBody().getFullunit() == null ? UnitBlockType.LightUnit : UnitBlockType.FullUnit;

        //交易數量
        Lightunit lightunit = unitBlockValue.getBody().getLightunit();

        int txCount = lightunit != null ? lightunit.getLightunit_input().getTxs().size() : 0;

        TopUnitBlock topUnitBlock = new TopUnitBlock();
        topUnitBlock.setAuditor(unitBlockValue.getHeader().getAuditor());
        topUnitBlock.setValidator(unitBlockValue.getHeader().getValidator());
        topUnitBlock.setPreHash(unitBlockValue.getPrev_hash());
        topUnitBlock.setHash(unitBlockValue.getHash());
        topUnitBlock.setHeight(unitBlockValue.getHeight());
        topUnitBlock.setZoneId(unitBlockValue.getZone_id());
        topUnitBlock.setShardId(unitBlockValue.getShard_id());
        topUnitBlock.setTableId(unitBlockValue.getTable_id());
        topUnitBlock.setClusterId(unitBlockValue.getCluster_id());
        topUnitBlock.setOwner(unitBlockValue.getOwner());
        topUnitBlock.setTimestamp(unitBlockValue.getTimestamp());
        topUnitBlock.setClusterId(unitBlockValue.getCluster_id());
        topUnitBlock.setTxCount(txCount);
        topUnitBlock.setBlockType(unitBlockType);
        topUnitBlock.setTableblockHash(tableBlockHash);
        unitBlockService.save(topUnitBlock);

        blockMessageProducer.sendNewUnitBlockMessage(topUnitBlock.getHash(), topUnitBlock.getOwner(), topUnitBlock.getHeight());
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitBlockParam {
        private String tableBlockHash;
        private String address;
        private Long height;

    }

}
