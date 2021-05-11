package org.topnetwork.analysis.block;

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
import org.topnetwork.grpclib.pojo.transaction.*;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

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

        TopTransaction topTransaction = topTransactionService.getTxByHash(txHash);

        if(topTransaction == null){
            topTransaction = new TopTransaction();
        }else if("success".equals(topTransaction.getTxState())){
            return true;
        }



        /***************************更新 交易信息***************************************/
        String from = tx_action.getSender_action().getTx_sender_account_addr();
        String to = tx_action.getReceiver_action().getTx_receiver_account_addr();
        topTransaction.setFrom(from);
        topTransaction.setTo(to);

        topTransaction.setAuthorization(txInfo.getAuthorization());
        topTransaction.setChallengeProof(txInfo.getChallenge_proof());
        topTransaction.setEdgeNodeId(txInfo.getEdge_nodeid());
        topTransaction.setExt(txInfo.getExt());
        topTransaction.setFromNetworkId(txInfo.getFrom_network_id());
        topTransaction.setToNetworkId(txInfo.getTo_network_id());
        topTransaction.setLastTxHash(txInfo.getLast_tx_hash());
        topTransaction.setLastTxNonce(txInfo.getLast_tx_nonce());
        topTransaction.setNote(txInfo.getNote());
        topTransaction.setParentAccount(txInfo.getParent_account());
        topTransaction.setSendTimestamp(txInfo.getSend_timestamp());
        topTransaction.setTxDeposit(txInfo.getTx_deposit());
        topTransaction.setTxExpireDuration(txInfo.getTx_expire_duration());
        topTransaction.setHash(txInfo.getTx_hash());
        topTransaction.setTxLen(txInfo.getTx_len());
        topTransaction.setTxRandomNonce(txInfo.getTx_random_nonce());
        topTransaction.setTxStructureVersion(txInfo.getTx_structure_version());
        topTransaction.setType(TxType.parse(txInfo.getTx_type()));
        topTransaction.setFromLedgerId(txInfo.getFrom_ledger_id());
        topTransaction.setToLedgerId(txInfo.getTo_ledger_id());
        topTransaction.setPremiumPrice(txInfo.getPremium_price());
        topTransaction.setTxState(txInfo.getTx_state());
        topTransaction.setChainZoneType(ZoneRuleUtils.getChainZoneType(tx_action.getSender_action().getTx_sender_account_addr()));


        /***************************更新tx_action***************************************/
        Sender_action senderAction = tx_action.getSender_action();
        Receiver_action receiverAction = tx_action.getReceiver_action();

        Action_param senderParam = senderAction.getAction_param();
        Action_param receiverParam = receiverAction.getAction_param();


        /***************************更新sender_action***************************************/
        topTransaction.setSenderActionAutorization(senderAction.getAction_authorization());
        topTransaction.setSenderActionExt(senderAction.getAction_ext());
        topTransaction.setSenderActionHash(senderAction.getAction_hash());
        topTransaction.setSenderActionSize(senderAction.getAction_size());
        topTransaction.setSenderActionType(senderAction.getAction_type());
        topTransaction.setSenderActionAccountAddr(senderAction.getTx_sender_account_addr());
        if (!ObjectUtils.isEmpty(senderParam)) {
            topTransaction.setSenderActionParamFuncName(senderParam.getFunc_name());
            topTransaction.setSenderActionParamParas(senderParam.getParas());
            topTransaction.setSenderActionParamAddress(senderParam.getAddress());
            topTransaction.setSenderActionParamTokenName(senderParam.getToken_name());
            topTransaction.setSenderActionParamAmount(senderParam.getAmount());
            topTransaction.setAmount(senderParam.getAmount());
            topTransaction.setSenderActionParamTgasLimit(senderParam.getTgas_limit());
            topTransaction.setSenderActionParamCode(senderParam.getCode());
            topTransaction.setSenderActionParamVoteNum(senderParam.getVote_num());
            topTransaction.setSenderActionParamLockDuration(senderParam.getLock_duration());
            topTransaction.setSenderActionParamAccountKey(senderParam.getAccount_key());
            topTransaction.setSenderActionParamKeyValue(senderParam.getKey_value());
            topTransaction.setSenderActionParamVersion(senderParam.getVersion());
            topTransaction.setSenderActionParamUnlockType(senderParam.getUnlock_type());
            topTransaction.setSenderActionParamUnlockValues(senderParam.getUnlock_values());
            topTransaction.setSenderActionParamParams(senderParam.getParams());
            topTransaction.setSenderActionParamLockTranHash(senderParam.getLock_tran_hash());
            topTransaction.setSenderActionParamSignatures(senderParam.getSignatures());
            topTransaction.setSenderActionParamName(senderParam.getName());
        }

        /***************************更新sender_action***************************************/
        topTransaction.setReceiverActionAutorization(receiverAction.getAction_authorization());
        topTransaction.setReceiverActionExt(receiverAction.getAction_ext());
        topTransaction.setReceiverActionHash(receiverAction.getAction_hash());
        topTransaction.setReceiverActionSize(receiverAction.getAction_size());
        topTransaction.setReceiverActionType(receiverAction.getAction_type());
        topTransaction.setReceiverActionAccountAddr(receiverAction.getTx_receiver_account_addr());
        if (!ObjectUtils.isEmpty(receiverParam)) {
            topTransaction.setReceiverActionParamFuncName(receiverParam.getFunc_name());
            topTransaction.setReceiverActionParamParas(receiverParam.getParas());
            topTransaction.setReceiverActionParamAddress(receiverParam.getAddress());
            topTransaction.setReceiverActionParamTokenName(receiverParam.getToken_name());
            topTransaction.setReceiverActionParamAmount(receiverParam.getAmount());
            topTransaction.setReceiverActionParamTgasLimit(receiverParam.getTgas_limit());
            topTransaction.setReceiverActionParamCode(receiverParam.getCode());
            topTransaction.setReceiverActionParamVoteNum(receiverParam.getVote_num());
            topTransaction.setReceiverActionParamLockDuration(receiverParam.getLock_duration());
            topTransaction.setReceiverActionParamAccountKey(receiverParam.getAccount_key());
            topTransaction.setReceiverActionParamKeyValue(receiverParam.getKey_value());
            topTransaction.setReceiverActionParamVersion(receiverParam.getVersion());
            topTransaction.setReceiverActionParamUnlockType(receiverParam.getUnlock_type());
            topTransaction.setReceiverActionParamUnlockValues(receiverParam.getUnlock_values());
            topTransaction.setReceiverActionParamParams(receiverParam.getParams());
            topTransaction.setReceiverActionParamLockTranHash(receiverParam.getLock_tran_hash());
            topTransaction.setReceiverActionParamSignatures(receiverParam.getSignatures());
            topTransaction.setReceiverActionParamName(receiverParam.getName());
        }



        /***************************更新 unitblock信息***************************************/
        Tx_consensus_state state = transactionResult.getValue().getTx_consensus_state();

        UnitInfo sendUnitInfo = state.getSend_unit_info();
        UnitInfo recvUnitInfo = state.getRecv_unit_info();
        Confirm_unit_info confirmUnitInfo = state.getConfirm_unit_info();
        //更新交易发起unitblock信息
        if (!ObjectUtils.isEmpty(sendUnitInfo)) {
            topTransaction.setSendUnitUsedDeposit(sendUnitInfo.getUsed_deposit());
            topTransaction.setSendUnitUsedDisk(sendUnitInfo.getUsed_disk());
            topTransaction.setSendUnitUsedGas(sendUnitInfo.getUsed_gas());
            topTransaction.setSendUnitTxFee(sendUnitInfo.getTx_fee());
            topTransaction.setSendUnitHeight(sendUnitInfo.getHeight());
            topTransaction.setSendUnitHash(sendUnitInfo.getUnit_hash());;
        }
        //更新交易接收unitblock信息
        if (!ObjectUtils.isEmpty(recvUnitInfo)) {
            topTransaction.setRecvUnitUsedDeposit(recvUnitInfo.getUsed_deposit());
            topTransaction.setRecvUnitUsedDisk(recvUnitInfo.getUsed_disk());
            topTransaction.setRecvUnitUsedGas(recvUnitInfo.getUsed_gas());
            topTransaction.setRecvUnitHeight(recvUnitInfo.getHeight());
            topTransaction.setRecvUnitHash(recvUnitInfo.getUnit_hash());;
        }
        //更新确认unitblock信息
        if(!ObjectUtils.isEmpty(confirmUnitInfo)) {
            topTransaction.setConfirmUnitExecStatus(confirmUnitInfo.getExec_status());
            topTransaction.setConfirmUnitTxExecStatus(confirmUnitInfo.getTx_exec_status());
            topTransaction.setConfirmUnitUsedDeposit(confirmUnitInfo.getUsed_deposit());
            topTransaction.setConfirmUnitUsedGas(confirmUnitInfo.getUsed_gas());
            topTransaction.setConfirmUnitUsedDisk(confirmUnitInfo.getUsed_disk());
            topTransaction.setConfirmUnitHash(confirmUnitInfo.getUnit_hash());
            topTransaction.setConfirmUnitHeight(confirmUnitInfo.getHeight());
        }


        boolean txConfirmed = ObjectUtils.nullSafeEquals("success", topTransaction.getTxState());
        boolean saveTxSuccess = topTransactionService.saveOrUpdate(topTransaction);

        if(saveTxSuccess && txConfirmed){
            accountLoader.asyncLoadAccount(from);
            accountLoader.asyncLoadAccount(to);
            blockMessageProducer.sendNewTransactionMessage(topTransaction.getHash(), topTransaction.getFrom(), topTransaction.getTo(), topTransaction.getAmount());
        }

        return saveTxSuccess;
    }


}
