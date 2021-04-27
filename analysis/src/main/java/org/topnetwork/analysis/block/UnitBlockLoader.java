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
import org.topnetwork.common.entity.UnitBlock;
import org.topnetwork.common.enums.UnitBlockType;
import org.topnetwork.common.service.AccountService;
import org.topnetwork.common.service.UnitBlockService;
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
    AccountService accountService;

    @Autowired
    UnitBlockService unitBlockService;

    @Autowired
    BlockMessageProducer blockMessageProducer;

    @Autowired
    RocketMQTemplate rocketMQTemplate;


    @org.springframework.beans.factory.annotation.Value("${block.analysis.topic.unitblock}")
    private String asyncTopic;

    /**
     * 异步保存unitblock
     *
     * @param addr
     * @param height
     */
    public void asyncSaveNewUnitBlock(String addr, Long height) {
        rocketMQTemplate.syncSend(asyncTopic, new UnitBlockParam(addr, height));
//        saveNewUnitBlock(addr, height);
    }

    @Override
    public void onMessage(UnitBlockParam unitBlockParam) {
        saveNewUnitBlock(unitBlockParam.getAddr(), unitBlockParam.getHeight());
    }

    /**
     *
     * @param addr
     * @param height
     */
    @Transactional
    public void saveNewUnitBlock(String addr, Long height) {

        UnitBlockResult unitBlockResult = grpcClient.getUnitBlock(addr, height);

        saveToDB(unitBlockResult);
    }

    private void saveToDB(UnitBlockResult unitBlockResult) {
        Value value = unitBlockResult.getValue();

        UnitBlockType unitBlockType = value.getBody().getFullunit() == null ? UnitBlockType.LightUnit : UnitBlockType.FullUnit;

        //交易數量
        Lightunit lightunit = value.getBody().getLightunit();

        int txCount = lightunit != null ? lightunit.getLightunit_input().getTxs().size() : 0;

        UnitBlock unitBlock = new UnitBlock();
        unitBlock.setAuditor(value.getHeader().getAuditor());
        unitBlock.setValidator(value.getHeader().getValidator());
        unitBlock.setPreHash(value.getPrev_hash());
        unitBlock.setHash(value.getHash());
        unitBlock.setHeight(value.getHeight());
        unitBlock.setZoneId(value.getZone_id());
        unitBlock.setShardId(value.getShard_id());
        unitBlock.setTableId(value.getTable_id());
        unitBlock.setClusterId(value.getCluster_id());
        unitBlock.setOwner(value.getOwner());
        unitBlock.setTimestamp(value.getTimestamp());
        unitBlock.setClusterId(value.getCluster_id());
        unitBlock.setTxCount(txCount);
        unitBlock.setBlockType(unitBlockType);

        unitBlockService.save(unitBlock);

        blockMessageProducer.sendNewUnitBlockMessage(unitBlock.getHash(), unitBlock.getOwner(), unitBlock.getHeight());
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitBlockParam {
        private String addr;
        private Long height;

    }

}
