package org.topnetwork.analysis.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.topnetwork.analysis.dao.TransactionDao;
import org.topnetwork.analysis.service.AccountService;
import org.topnetwork.analysis.service.TransactionService;
import org.topnetwork.grpclib.pojo.account.AccountValue;
import org.topnetwork.grpclib.pojo.transaction.*;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;
import org.topnetwork.grpclib.xrpc.xrpc_request;

import java.util.HashMap;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    @Value("${top.grpcip}")
    private String ip;
    @Value("${top.grpcport}")
    private int port;

    @Value("${account.rocketmq.topic}")
    private String topic;

    @Value("${account.rocketmq.tag}")
    private String target;

    @SuppressWarnings("unused")
    private TransactionDao transactionDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncFormChain(String txHash) {
        TopGrpcClient instance = TopGrpcClient.getInstance(ip, port);
        //根据hash查询交易详情，
        HashMap<String, Object> map = new HashMap<>();
        map.put("tx_hash", txHash);
        System.out.println("========tx_hash:" + txHash);

        xrpc_request request = instance.createRequest("getTransaction", map);
        String tx = instance.callRequest(request);


        TransactionResult transactionResult = null;
        transactionResult = JSON.parseObject(tx,TransactionResult.class);
        if (ObjectUtils.isEmpty(transactionResult)
                || "transaction not found".equalsIgnoreCase(transactionResult.getResult())
                || "transaction not find".equalsIgnoreCase(transactionResult.getResult())
                || ObjectUtils.isEmpty(transactionResult.getValue())) {
            LOG.info(transactionResult.getResult() + " >> hash > " + txHash);
            return;
        }
    }

    @Override
    public Boolean saveTxToDb(TransactionResult transactionResult, HashMap<String, AccountValue> shardIds, String tx_hash) {
        Tx_action tx_action = transactionResult.getValue().getOriginal_tx_info().getTx_action();
        Original_tx_info original_tx_info = transactionResult.getValue().getOriginal_tx_info();
        Confirm_unit_info confirm_unit_info = transactionResult.getValue().getTx_consensus_state().getConfirm_unit_info();
        Action_param target = tx_action.getReceiver_action().getAction_param();
        Action_param source = tx_action.getSender_action().getAction_param();

        Boolean confirmSuccess = false;

        if (!ObjectUtils.isEmpty(confirm_unit_info.getExec_status()) && "success".equalsIgnoreCase(confirm_unit_info.getExec_status())) {
            confirmSuccess = true;
        }

        return confirmSuccess;
    }

    private void asyncSendAccountMq(String topic, String address) {
        rocketMQTemplate.asyncSend(topic, address, null);
    }


}
