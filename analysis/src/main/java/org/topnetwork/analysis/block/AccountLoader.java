package org.topnetwork.analysis.block;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.topnetwork.analysis.mq.BlockMessageProducer;
import org.topnetwork.analysis.utils.TopUtils;
import org.topnetwork.analysis.utils.ZoneRuleUtils;
import org.topnetwork.common.entity.Account;
import org.topnetwork.common.service.AccountService;
import org.topnetwork.grpclib.pojo.account.AccountResult;
import org.topnetwork.grpclib.pojo.account.AccountValue;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.math.BigInteger;

/**
 * @author CasonCai
 * @since 2021/4/21 下午3:58
 **/
@Slf4j
@Component
@RocketMQMessageListener(topic = "${block.analysis.topic.account}",
        consumerGroup = "${block.analysis.consumegroup.account}")
public class AccountLoader implements RocketMQListener<String> {

    @Autowired
    TopGrpcClient grpcClient;

    @Autowired
    AccountService accountService;

    @Autowired
    BlockMessageProducer blockMessageProducer;
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Value("${block.analysis.topic.account}")
    private String asyncTopic;

    public void asyncLoadAccount(String address){
        rocketMQTemplate.sendOneWay(asyncTopic, address);
    }

    public void loadAccount(String address){
        AccountResult accountResult = grpcClient.getAccount(address);
        AccountValue accountValue = accountResult.getValue();
        if(ObjectUtils.isEmpty(accountValue)){
            log.info("known account that address = {}", address);
            return;
        }

        Account account = accountService.getByAddress(accountValue.getAccount_addr());
        if (ObjectUtils.isEmpty(account)) {
            //如果为空就保存
            account = new Account();
            //交易数
            account.setTxNum(BigInteger.ONE);

        } else {
            //不为空就更新
            account.setTxNum(account.getTxNum().add(BigInteger.ONE));
            blockMessageProducer.sendNewAccountMessage(accountValue.getAccount_addr());
        }


        account.setAddress(accountValue.getAccount_addr());
        account.setBalance(accountValue.getBalance());
        account.setBurnedToken(accountValue.getBurned_token());
        account.setChainZoneType(ZoneRuleUtils.getChainZoneType(accountValue.getAccount_addr()));
        account.setClusterId(accountValue.getZone_id());
        account.setDiskStakedToken(accountValue.getDisk_staked_token());
        account.setGasStakedToken(accountValue.getGas_staked_token());
        account.setGasTotal(accountValue.getTotal_gas());
        account.setGasUnUse(accountValue.getAvailable_gas());
        account.setLockBalance(accountValue.getLock_balance());
        account.setLatestTxHash(accountValue.getLatest_tx_hash());
        account.setLatestTxHashXxhash64(accountValue.getLatest_tx_hash_xxhash64());
        account.setLockDepositBalance(accountValue.getLock_deposit_balance());
        account.setLockGas(accountValue.getLock_gas());
        account.setNonce(accountValue.getNonce());
        account.setShard(accountValue.getGroup_id());
        account.setType(TopUtils.getAccountType(accountValue.getAccount_addr()).toString());
        account.setUnlockDiskStaked(accountValue.getUnlock_disk_staked());
        account.setUnlockGasStaked(accountValue.getUnlock_gas_staked());
        account.setUnusedVoteAmount(accountValue.getUnused_vote_amount());
        account.setVoteStakedToken(accountValue.getVote_staked_token());
        account.setZoneId(accountValue.getZone_id());
        account.setTimestamp(accountValue.getCreated_time());

        accountService.saveOrUpdate(account);
    }

    private void updateAccount(AccountResult accountResult){
        AccountValue accountValue = accountResult.getValue();

        Account account = accountService.getByAddress(accountValue.getAccount_addr());
        if (ObjectUtils.isEmpty(account)) {
            //如果为空就保存
            account = new Account();
            //交易数
            account.setTxNum(BigInteger.ONE);
            blockMessageProducer.sendNewAccountMessage(accountValue.getAccount_addr());
        } else {
            //不为空就更新
            account.setTxNum(account.getTxNum().add(BigInteger.ONE));
        }

        account.setAddress(accountValue.getAccount_addr());
        account.setBalance(accountValue.getBalance());
        account.setBurnedToken(accountValue.getBurned_token());
        account.setChainZoneType(ZoneRuleUtils.getChainZoneType(accountValue.getAccount_addr()));
        account.setClusterId(accountValue.getZone_id());
        account.setDiskStakedToken(accountValue.getDisk_staked_token());
        account.setGasStakedToken(accountValue.getGas_staked_token());
        account.setGasTotal(accountValue.getTotal_gas());
        account.setGasUnUse(accountValue.getAvailable_gas());
        account.setLockBalance(accountValue.getLock_balance());
        account.setLatestTxHash(accountValue.getLatest_tx_hash());
        account.setLatestTxHashXxhash64(accountValue.getLatest_tx_hash_xxhash64());
        account.setLockDepositBalance(accountValue.getLock_deposit_balance());
        account.setLockGas(accountValue.getLock_gas());
        account.setNonce(accountValue.getNonce());
        account.setShard(accountValue.getGroup_id());
        account.setType(TopUtils.getAccountType(accountValue.getAccount_addr()).toString());
        account.setUnlockDiskStaked(accountValue.getUnlock_disk_staked());
        account.setUnlockGasStaked(accountValue.getUnlock_gas_staked());
        account.setUnusedVoteAmount(accountValue.getUnused_vote_amount());
        account.setVoteStakedToken(accountValue.getVote_staked_token());
        account.setZoneId(accountValue.getZone_id());
        account.setTimestamp(accountValue.getCreated_time());

        accountService.saveOrUpdate(account);
    }


    @Override
    public void onMessage(String address) {
        loadAccount(address);
    }
}
