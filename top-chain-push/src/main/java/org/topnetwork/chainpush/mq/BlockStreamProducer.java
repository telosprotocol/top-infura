package org.topnetwork.chainpush.mq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlockStreamProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(String topic, Object message){
        SendResult sendResult = rocketMQTemplate.syncSend(topic, message);
        return sendResult;
    }
}
