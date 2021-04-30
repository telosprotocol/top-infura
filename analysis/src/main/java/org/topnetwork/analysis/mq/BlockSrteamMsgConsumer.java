package org.topnetwork.analysis.mq;

import org.topnetwork.analysis.service.TableBlockService;
import org.topnetwork.grpclib.pojo.stream.TableBlockResult;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: api service
 * @description: 接收区块信息
 **/
//@Component
@RocketMQMessageListener(topic = "${pull.rocketmq.topic}", consumerGroup = "${rocketmq.consumer.group}",
        selectorExpression = "${pull.rocketmq.tag}",messageModel =  MessageModel.CLUSTERING)
public class BlockSrteamMsgConsumer implements RocketMQListener<TableBlockResult> {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private TableBlockService tableBlockService;

    @Override
    public void onMessage(TableBlockResult message) {
            LOGGER.info("receive msg start..." + message);
            try {
                tableBlockService.queryTxByHash(message);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("===============total error============" + e.getMessage());
                throw e;
            }
    }
}
