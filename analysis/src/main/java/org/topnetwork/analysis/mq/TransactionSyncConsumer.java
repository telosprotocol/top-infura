package org.topnetwork.analysis.mq;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.topnetwork.analysis.service.TransactionService;

/**
 * @program: api service
 * @description: 接收交易hash，并同步交易信息
 * @author: sawyer
 * @create: 2021-04-09 09:22:32
 **/
//@Component
@RocketMQMessageListener(topic = "${transaction.rocketmq.topic}", consumerGroup = "transaction225",
        selectorExpression = "${transaction.rocketmq.tag}",messageModel =  MessageModel.CLUSTERING)
public class TransactionSyncConsumer implements RocketMQListener<String> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private TransactionService transactionService;

    @Override
    public void onMessage(String txHash) {
        LOGGER.info("receive tx hash..." + txHash);
        try {
            transactionService.syncFormChain(txHash);
        } catch (Exception e) {
            LOGGER.error("handle tx sync error, txHash: " + txHash);
            e.printStackTrace();
            throw e;
        }
    }
}
