package org.topnetwork.analysis.block;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.topnetwork.analysis.mq.BlockMessageProducer;
import org.topnetwork.analysis.utils.ZoneRuleUtils;
import org.topnetwork.common.entity.Transaction;
import org.topnetwork.common.enums.TxType;
import org.topnetwork.common.service.TransactionService;
import org.topnetwork.grpclib.pojo.account.AccountResult;
import org.topnetwork.grpclib.pojo.transaction.*;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.math.BigInteger;

/**
 * 交易数据加载器
 * @author CasonCai
 * @since 2021/4/20 下午7:25
 **/
@Slf4j
@Component
@RocketMQMessageListener(topic = "${block.analysis.topic.transaction}",
        consumerGroup = "${block.analysis.consumegroup.transaction}")
public class TransactionLoader implements RocketMQListener<String> {

    @Autowired
    TopGrpcClient topGrpcClient;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountLoader accountLoader;

    @Autowired
    BlockMessageProducer blockMessageProducer;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    //异步消息队列名称
    @Value("${block.analysis.topic.transaction}")
    private String asyncTopic;

    public void asyncLoadTransaction(String txHash){
        rocketMQTemplate.sendOneWay(asyncTopic, txHash);
    }

    @Override
    public void onMessage(String txHash) {
        try{
            loadTransaction(txHash);
        }catch (Exception e){
            log.error("load transaction error, txHash=" + txHash, e);
        }
    }

    @Transactional
    public void loadTransaction(String txHash){
        TransactionResult transactionResult = topGrpcClient.getTransaction(txHash);
        if(transactionResult == null || transactionResult.getValue() == null){
            log.info("unkown transaction that txHash = {}",txHash);
            return;
        }

        saveToDB(transactionResult);
    }


    /**
     * 储存至db
     * @param transactionResult
     * @return
     */
    public Boolean saveToDB(TransactionResult transactionResult){

        Original_tx_info txInfo = transactionResult.getValue().getOriginal_tx_info();
        Tx_action tx_action = txInfo.getTx_action();
        //hash
        String txHash = txInfo.getTx_hash();
        String from = tx_action.getSender_action().getTx_sender_account_addr();
        String to = tx_action.getReceiver_action().getTx_receiver_account_addr();

        Transaction transaction = transactionService.getTxByHash(txHash);

        if(transaction == null){
            transaction = new Transaction();
        }

        Tx_consensus_state state = transactionResult.getValue().getTx_consensus_state();


        transaction.setHash(txHash);
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setType(TxType.parse(txInfo.getTx_type()));
        transaction.setNote(txInfo.getNote());
        transaction.setLastTxNonce(txInfo.getLast_tx_nonce());
        transaction.setTxLen(txInfo.getTx_len());

        //sender字段
        Action_param senderParam = tx_action.getSender_action().getAction_param();
        Action_param receiverParam = tx_action.getReceiver_action().getAction_param();
        transaction.setSenderActionParam(JSON.toJSONString(senderParam));
        transaction.setReceiverActionParam(JSON.toJSONString(receiverParam));
        if (ObjectUtils.isEmpty(senderParam)) {
            transaction.setAmount(BigInteger.ZERO);
        } else {
            transaction.setAmount(new BigInteger(senderParam.getAmount()));
            transaction.setTokenName(senderParam.getToken_name());
            transaction.setAccountKey(senderParam.getAccount_key());
            transaction.setKeyValue(senderParam.getKey_value());
            transaction.setTgasLimit(senderParam.getTgas_limit());
        }
        //receiver字段
        if (!ObjectUtils.isEmpty(receiverParam)) {
            transaction.setFuncName(receiverParam.getFunc_name());
            transaction.setFuncInput(receiverParam.getParas());
            transaction.setTgasLimit(receiverParam.getTgas_limit());
            transaction.setCode(receiverParam.getCode());
            transaction.setVoteNum(receiverParam.getVote_num());
            transaction.setLockDuration(receiverParam.getLock_duration());
            transaction.setAccountKey(receiverParam.getAccount_key());
            transaction.setKeyValue(receiverParam.getKey_value());
            transaction.setVersion(receiverParam.getVersion());
            transaction.setUnlockType(receiverParam.getUnlock_type());
            transaction.setUnlockValues(receiverParam.getUnlock_values());
            transaction.setParams(receiverParam.getParams());
            transaction.setLockTranHash(receiverParam.getLock_tran_hash());
            transaction.setSignatures(receiverParam.getSignatures());
            transaction.setName(receiverParam.getName());
        }

        transaction.setTimestamp(txInfo.getSend_timestamp());
        transaction.setTxDeposit(txInfo.getTx_deposit());



        transaction.setChainZoneType(ZoneRuleUtils.getChainZoneType(tx_action.getSender_action().getTx_sender_account_addr()));

        //查询from to账号shard
        AccountResult fromAccount = topGrpcClient.getAccount(from);
        AccountResult toAccount = topGrpcClient.getAccount(to);
        transaction.setShardFrom(fromAccount.getValue().getGroup_id());
        transaction.setShardTo(toAccount.getValue().getGroup_id());


        //更新交易的unitblock信息
        UnitInfo sendUnitInfo = state.getSend_unit_info();
        UnitInfo recvUnitInfo = state.getRecv_unit_info();
        Confirm_unit_info confirmUnitInfo = state.getConfirm_unit_info();
        //更新交易发起unitblock信息
        if (!ObjectUtils.isEmpty(sendUnitInfo)) {
            transaction.setSendUsedDeposit(sendUnitInfo.getUsed_deposit());
            transaction.setSendUsedDisk(sendUnitInfo.getUsed_disk());
            transaction.setSendUsedGas(sendUnitInfo.getUsed_gas());
            transaction.setTxFee(sendUnitInfo.getTx_fee());
            transaction.setSendUnitBlockHash(sendUnitInfo.getUnit_hash());;
        }
        //更新交易接收unitblock信息
        if (!ObjectUtils.isEmpty(recvUnitInfo)) {
            transaction.setRecvUsedDeposit(recvUnitInfo.getUsed_deposit());
            transaction.setRecvUsedDisk(recvUnitInfo.getUsed_disk());
            transaction.setRecvUsedGas(recvUnitInfo.getUsed_gas());
            transaction.setRecUnitBlockHash(recvUnitInfo.getUnit_hash());;
        }
        //更新确认unitblock信息
        if(!ObjectUtils.isEmpty(confirmUnitInfo)) {
            transaction.setStatus(confirmUnitInfo.getExec_status());
            transaction.setUsedDeposit(confirmUnitInfo.getUsed_deposit());
            transaction.setGasUsed(confirmUnitInfo.getUsed_gas());
            transaction.setDiskUsed(confirmUnitInfo.getUsed_disk());
            transaction.setConfirmUnitBlockHash(confirmUnitInfo.getUnit_hash());
        }

        boolean txConfirmed = ObjectUtils.nullSafeEquals("success", transaction.getStatus());
        boolean saveTxSuccess = transactionService.saveOrUpdate(transaction);

        if(saveTxSuccess && txConfirmed){
            accountLoader.asyncLoadAccount(from);
            accountLoader.asyncLoadAccount(to);
            blockMessageProducer.sendNewTransactionMessage(transaction.getHash(), transaction.getFrom(), transaction.getTo(), transaction.getAmount());
        }

        return saveTxSuccess;
    }


}
