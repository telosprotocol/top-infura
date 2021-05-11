package org.topnetwork.analysis.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.topnetwork.analysis.mq.message.NewAccountMessage;
import org.topnetwork.analysis.mq.message.NewTransactionMessage;
import org.topnetwork.analysis.mq.message.NewUnitBlockMessage;

import java.math.BigInteger;

/**
 * @author CasonCai
 * @since 2021/4/22 下午2:16
 **/
@Slf4j
@Component
public class BlockMessageProducer {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Value("${block.message.topic.unitblock}")
    String unitBlockTopic;
    @Value("${block.message.topic.transaction}")
    String transactionTopic;

    @Value("${block.message.topic.account}")
    String accountTopic;

    public void sendNewUnitBlockMessage(String hash, String owner, Long height){

        NewUnitBlockMessage unitBlockMessage = new NewUnitBlockMessage(hash, owner, height);

        rocketMQTemplate.asyncSend(unitBlockTopic, unitBlockMessage,new BlockMessageSendCallback(unitBlockMessage));

    }

    public void sendNewTransactionMessage(String txHash, String from, String to, BigInteger amount){

        NewTransactionMessage transactionMessage = new NewTransactionMessage(txHash, from, to, amount);

        rocketMQTemplate.asyncSend(transactionTopic, transactionMessage,new BlockMessageSendCallback(transactionMessage));
    }

    public void sendNewAccountMessage(String address){

        NewAccountMessage accountMessage = new NewAccountMessage(address);

        rocketMQTemplate.asyncSend(accountTopic, accountMessage,new BlockMessageSendCallback(accountMessage));

    }


    public static class BlockMessageSendCallback implements SendCallback{

        Object content;

        public BlockMessageSendCallback(Object content) {
            this.content = content;
        }

        @Override
        public void onSuccess(SendResult sendResult) {
            log.debug("send success, content={}", JSON.toJSONString(content));
        }

        @Override
        public void onException(Throwable throwable) {
            log.error("send fail, content=" + JSON.toJSONString(content), throwable);

        }
    }

}
