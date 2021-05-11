package org.topnetwork.analysis.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.topnetwork.analysis.mq.BlockMessageProducer;
import org.topnetwork.common.entity.TopUnitBlock;
import org.topnetwork.common.enums.UnitBlockType;
import org.topnetwork.common.service.TopAccountService;
import org.topnetwork.common.service.TopUnitBlockService;
import org.topnetwork.grpclib.pojo.unit.*;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.math.BigInteger;

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
        rocketMQTemplate.sendOneWay(asyncTopic, new UnitBlockParam(tableBlockHash,address, height));
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
        try {
            saveToDB(tableBlockHash, unitBlockResult);
        }catch (DuplicateKeyException duplicateE){
            log.info("duplicate unitblock hash={}", unitBlockResult.getValue().getHash());
        }
    }

    private void saveToDB(String tableBlockHash, UnitBlockResult unitBlockResult) {
        UnitBlockValue unitBlockValue = unitBlockResult.getValue();

        UnitBlockType unitBlockType = unitBlockValue.getBody().getFullunit() == null ? UnitBlockType.LightUnit : UnitBlockType.FullUnit;

        //交易數量
        Lightunit lightunit = unitBlockValue.getBody().getLightunit();

        int txCount = lightunit != null ? lightunit.getLightunit_input().getTxs().size() : 0;
        BigInteger balanceChange = lightunit!= null? lightunit.getLightunit_state().getBalance_change() : null;
        BigInteger burnedAmountChange = lightunit != null? lightunit.getLightunit_state().getBurned_amount_change() : null;


        TopUnitBlock topUnitBlock = new TopUnitBlock();
        topUnitBlock.setAuditor(unitBlockValue.getHeader().getAuditor());
        topUnitBlock.setValidator(unitBlockValue.getHeader().getValidator());
        topUnitBlock.setPrevHash(unitBlockValue.getPrev_hash());
        topUnitBlock.setHash(unitBlockValue.getHash());
        topUnitBlock.setHeight(unitBlockValue.getHeight());
        topUnitBlock.setZoneId(unitBlockValue.getZone_id());
        topUnitBlock.setShardId(unitBlockValue.getShard_id());
        topUnitBlock.setTableId(unitBlockValue.getTable_id());
        topUnitBlock.setClusterId(unitBlockValue.getCluster_id());
        topUnitBlock.setOwner(unitBlockValue.getOwner());
        topUnitBlock.setTimerHeight(unitBlockValue.getHeader().getTimerblock_height());
        topUnitBlock.setTimestamp(unitBlockValue.getTimestamp());
        topUnitBlock.setClusterId(unitBlockValue.getCluster_id());
        topUnitBlock.setTxCount(txCount);
        topUnitBlock.setBlockType(unitBlockType);
        topUnitBlock.setTableblockHash(tableBlockHash);
        topUnitBlock.setCode(unitBlockValue.getCode());
        topUnitBlock.setDiskRedeemNum(unitBlockValue.getDisk_redeem_num());
        topUnitBlock.setTgasRedeemNum(unitBlockValue.getTgas_redeem_num());
        topUnitBlock.setAuditorXip(unitBlockValue.getHeader().getAuditor_xip());
        topUnitBlock.setValidatorXip(unitBlockValue.getHeader().getValidator_xip());
        topUnitBlock.setBalanceChange(balanceChange);
        topUnitBlock.setBurnedAmountChange(burnedAmountChange);

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
