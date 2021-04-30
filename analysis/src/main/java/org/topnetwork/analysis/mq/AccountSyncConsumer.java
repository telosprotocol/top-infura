package org.topnetwork.analysis.mq;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.topnetwork.analysis.service.AccountService;

//@Component
//@RocketMQMessageListener(topic = "${account.rocketmq.topic}", consumerGroup = "account225",
//        selectorExpression = "${account.rocketmq.tag}",messageModel =  MessageModel.CLUSTERING)
public class AccountSyncConsumer implements RocketMQListener<String> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService accountService;

    @Override
    public void onMessage(String accountAddress) {
        LOGGER.info("receive account address..." + accountAddress);
        try {
            accountService.syncFormChain(accountAddress);
        } catch (Exception e) {
            LOGGER.error("handle account sync error, account address: " + accountAddress);
            e.printStackTrace();
            throw e;
        }
    }
}
