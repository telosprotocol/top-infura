package org.topnetwork.analysis.service.impl;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.topnetwork.analysis.service.TableBlockService;
import org.topnetwork.analysis.service.UnitBlockService;
import org.topnetwork.common.dao.TableBlockDao;
import org.topnetwork.grpclib.pojo.stream.LightUnitInput;
import org.topnetwork.grpclib.pojo.stream.TableBlockResult;
import org.topnetwork.grpclib.pojo.stream.UnitsBlockMap;

import java.util.HashMap;

/**
 * @author pc
 */
public class TableBlockServiceImpl implements TableBlockService {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${transaction.rocketmq.topic}")
    private String topic;

    @Value("${transaction.rocketmq.tag}")
    private String target;

    @Autowired
    private UnitBlockService unitBlockService;

    @SuppressWarnings("unused")
    private TableBlockDao tableBlockDao;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void queryTxByHash(TableBlockResult message) {
        //如果内部有交易
        if (ObjectUtils.isEmpty(message.getValue().getBody().getTableblock())) {
            return;
        }
        HashMap<String, UnitsBlockMap> units = message.getValue().getBody().getTableblock().getUnits();
        //存储单元块出块数据
        unitBlockService.saveUnitBlock(units);
        for (String k : units.keySet()) {
            HashMap<String, LightUnitInput> lightunit_input = units.get(k).getLightunit_input();
            if (ObjectUtils.isEmpty(lightunit_input) || ObjectUtils.isEmpty(lightunit_input.keySet())) {
                continue;
            }
            for (String hash : lightunit_input.keySet()) {
                String txTopic = topic + ":" + target;
                rocketMQTemplate.asyncSend(txTopic, hash, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        LOGGER.info("send tx hash success > " + hash);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        LOGGER.error("send tx hash error > " + hash);
                        throwable.printStackTrace();
                    }
                });
            }
        }
    }
}
