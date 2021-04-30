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
import org.topnetwork.common.entity.TopTransaction;
import org.topnetwork.common.enums.TxType;
import org.topnetwork.common.service.TopTransactionService;
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
    TopTransactionService topTransactionService;

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

        TopTransaction topTransaction = topTransactionService.getTxByHash(txHash);

        if(topTransaction == null){
            topTransaction = new TopTransaction();
        }

        Tx_consensus_state state = transactionResult.getValue().getTx_consensus_state();


        topTransaction.setHash(txHash);
        topTransaction.setFrom(from);
        topTransaction.setTo(to);
        topTransaction.setType(TxType.parse(txInfo.getTx_type()));
        topTransaction.setNote(txInfo.getNote());
        topTransaction.setLastTxNonce(txInfo.getLast_tx_nonce());
        topTransaction.setTxLen(txInfo.getTx_len());

        //sender字段
        Action_param senderParam = tx_action.getSender_action().getAction_param();
        Action_param receiverParam = tx_action.getReceiver_action().getAction_param();
        topTransaction.setSenderActionParam(JSON.toJSONString(senderParam));
        topTransaction.setReceiverActionParam(JSON.toJSONString(receiverParam));
        if (ObjectUtils.isEmpty(senderParam)) {
            topTransaction.setAmount(BigInteger.ZERO);
        } else {
            topTransaction.setAmount(new BigInteger(senderParam.getAmount()));
            topTransaction.setTokenName(senderParam.getToken_name());
            topTransaction.setAccountKey(senderParam.getAccount_key());
            topTransaction.setKeyValue(senderParam.getKey_value());
            topTransaction.setTgasLimit(senderParam.getTgas_limit());
        }
        //receiver字段
        if (!ObjectUtils.isEmpty(receiverParam)) {
            topTransaction.setFuncName(receiverParam.getFunc_name());
            topTransaction.setFuncInput(receiverParam.getParas());
            topTransaction.setTgasLimit(receiverParam.getTgas_limit());
            topTransaction.setCode(receiverParam.getCode());
            topTransaction.setVoteNum(receiverParam.getVote_num());
            topTransaction.setLockDuration(receiverParam.getLock_duration());
            topTransaction.setAccountKey(receiverParam.getAccount_key());
            topTransaction.setKeyValue(receiverParam.getKey_value());
            topTransaction.setVersion(receiverParam.getVersion());
            topTransaction.setUnlockType(receiverParam.getUnlock_type());
            topTransaction.setUnlockValues(receiverParam.getUnlock_values());
            topTransaction.setParams(receiverParam.getParams());
            topTransaction.setLockTranHash(receiverParam.getLock_tran_hash());
            topTransaction.setSignatures(receiverParam.getSignatures());
            topTransaction.setName(receiverParam.getName());
        }

        topTransaction.setTimestamp(txInfo.getSend_timestamp());
        topTransaction.setTxDeposit(txInfo.getTx_deposit());



        topTransaction.setChainZoneType(ZoneRuleUtils.getChainZoneType(tx_action.getSender_action().getTx_sender_account_addr()));

        //查询from to账号shard
        AccountResult fromAccount = topGrpcClient.getAccount(from);
        AccountResult toAccount = topGrpcClient.getAccount(to);
        topTransaction.setShardFrom(fromAccount.getValue().getGroup_id());
        topTransaction.setShardTo(toAccount.getValue().getGroup_id());


        //更新交易的unitblock信息
        UnitInfo sendUnitInfo = state.getSend_unit_info();
        UnitInfo recvUnitInfo = state.getRecv_unit_info();
        Confirm_unit_info confirmUnitInfo = state.getConfirm_unit_info();
        //更新交易发起unitblock信息
        if (!ObjectUtils.isEmpty(sendUnitInfo)) {
            topTransaction.setSendUsedDeposit(sendUnitInfo.getUsed_deposit());
            topTransaction.setSendUsedDisk(sendUnitInfo.getUsed_disk());
            topTransaction.setSendUsedGas(sendUnitInfo.getUsed_gas());
            topTransaction.setTxFee(sendUnitInfo.getTx_fee());
            topTransaction.setSendUnitBlockHash(sendUnitInfo.getUnit_hash());;
        }
        //更新交易接收unitblock信息
        if (!ObjectUtils.isEmpty(recvUnitInfo)) {
            topTransaction.setRecvUsedDeposit(recvUnitInfo.getUsed_deposit());
            topTransaction.setRecvUsedDisk(recvUnitInfo.getUsed_disk());
            topTransaction.setRecvUsedGas(recvUnitInfo.getUsed_gas());
            topTransaction.setRecUnitBlockHash(recvUnitInfo.getUnit_hash());;
        }
        //更新确认unitblock信息
        if(!ObjectUtils.isEmpty(confirmUnitInfo)) {
            topTransaction.setStatus(confirmUnitInfo.getExec_status());
            topTransaction.setUsedDeposit(confirmUnitInfo.getUsed_deposit());
            topTransaction.setGasUsed(confirmUnitInfo.getUsed_gas());
            topTransaction.setDiskUsed(confirmUnitInfo.getUsed_disk());
            topTransaction.setConfirmUnitBlockHash(confirmUnitInfo.getUnit_hash());
        }

        boolean txConfirmed = ObjectUtils.nullSafeEquals("success", topTransaction.getStatus());
        boolean saveTxSuccess = topTransactionService.saveOrUpdate(topTransaction);

        if(saveTxSuccess && txConfirmed){
            accountLoader.asyncLoadAccount(from);
            accountLoader.asyncLoadAccount(to);
            blockMessageProducer.sendNewTransactionMessage(topTransaction.getHash(), topTransaction.getFrom(), topTransaction.getTo(), topTransaction.getAmount());
        }

        return saveTxSuccess;
    }


}
