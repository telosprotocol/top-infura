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
import org.topnetwork.analysis.utils.ZoneRuleUtils;
import org.topnetwork.common.entity.TopAccount;
import org.topnetwork.common.enums.AccountTypeEnum;
import org.topnetwork.common.service.TopAccountService;
import org.topnetwork.common.utils.TopUtils;
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
    TopAccountService topAccountService;

    @Autowired
    BlockMessageProducer blockMessageProducer;
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Value("${block.analysis.topic.account}")
    private String asyncTopic;

    public void asyncLoadAccount(String address) {
        rocketMQTemplate.sendOneWay(asyncTopic, address);
    }

    public void loadAccount(String address) {
        AccountResult accountResult = grpcClient.getAccount(address);
        AccountValue accountValue = accountResult.getValue();
        if (ObjectUtils.isEmpty(accountValue)) {
            log.info("known account that address = {}", address);
            return;
        }

        TopAccount topAccount = topAccountService.getByAddress(accountValue.getAccount_addr());
        if (ObjectUtils.isEmpty(topAccount)) {
            //如果为空就保存
            topAccount = new TopAccount();
            //交易数
            topAccount.setTxNum(BigInteger.ONE);

        } else {
            //不为空就更新
            topAccount.setTxNum(topAccount.getTxNum().add(BigInteger.ONE));
            blockMessageProducer.sendNewAccountMessage(accountValue.getAccount_addr());
        }


        topAccount.setAddress(accountValue.getAccount_addr());
        topAccount.setBalance(accountValue.getBalance());
        topAccount.setBurnedToken(accountValue.getBurned_token());
        topAccount.setChainZoneType(ZoneRuleUtils.getChainZoneType(accountValue.getAccount_addr()));
        topAccount.setClusterId(accountValue.getZone_id());
        topAccount.setDiskStakedToken(accountValue.getDisk_staked_token());
        topAccount.setGasStakedToken(accountValue.getGas_staked_token());
        topAccount.setGasTotal(accountValue.getTotal_gas());
        topAccount.setGasUnUse(accountValue.getAvailable_gas());
        topAccount.setLockBalance(accountValue.getLock_balance());
        topAccount.setLatestTxHash(accountValue.getLatest_tx_hash());
        topAccount.setLatestTxHashXxhash64(accountValue.getLatest_tx_hash_xxhash64());
        topAccount.setLockDepositBalance(accountValue.getLock_deposit_balance());
        topAccount.setLockGas(accountValue.getLock_gas());
        topAccount.setNonce(accountValue.getNonce());
        topAccount.setShard(accountValue.getGroup_id());
        topAccount.setType(AccountTypeEnum.ofAddress(accountValue.getAccount_addr()).toString());
        topAccount.setUnlockDiskStaked(accountValue.getUnlock_disk_staked());
        topAccount.setUnlockGasStaked(accountValue.getUnlock_gas_staked());
        topAccount.setUnusedVoteAmount(accountValue.getUnused_vote_amount());
        topAccount.setVoteStakedToken(accountValue.getVote_staked_token());
        topAccount.setZoneId(accountValue.getZone_id());
        topAccount.setTimestamp(accountValue.getCreated_time());

        topAccountService.saveOrUpdate(topAccount);
    }

    private void updateAccount(AccountResult accountResult) {
        AccountValue accountValue = accountResult.getValue();

        TopAccount topAccount = topAccountService.getByAddress(accountValue.getAccount_addr());
        if (ObjectUtils.isEmpty(topAccount)) {
            //如果为空就保存
            topAccount = new TopAccount();
            //交易数
            topAccount.setTxNum(BigInteger.ONE);
            blockMessageProducer.sendNewAccountMessage(accountValue.getAccount_addr());
        } else {
            //不为空就更新
            topAccount.setTxNum(topAccount.getTxNum().add(BigInteger.ONE));
        }

        topAccount.setAddress(accountValue.getAccount_addr());
        topAccount.setBalance(accountValue.getBalance());
        topAccount.setBurnedToken(accountValue.getBurned_token());
        topAccount.setChainZoneType(ZoneRuleUtils.getChainZoneType(accountValue.getAccount_addr()));
        topAccount.setClusterId(accountValue.getZone_id());
        topAccount.setDiskStakedToken(accountValue.getDisk_staked_token());
        topAccount.setGasStakedToken(accountValue.getGas_staked_token());
        topAccount.setGasTotal(accountValue.getTotal_gas());
        topAccount.setGasUnUse(accountValue.getAvailable_gas());
        topAccount.setLockBalance(accountValue.getLock_balance());
        topAccount.setLatestTxHash(accountValue.getLatest_tx_hash());
        topAccount.setLatestTxHashXxhash64(accountValue.getLatest_tx_hash_xxhash64());
        topAccount.setLockDepositBalance(accountValue.getLock_deposit_balance());
        topAccount.setLockGas(accountValue.getLock_gas());
        topAccount.setNonce(accountValue.getNonce());
        topAccount.setShard(accountValue.getGroup_id());
        topAccount.setType(TopUtils.getAccountType(accountValue.getAccount_addr()).toString());
        topAccount.setUnlockDiskStaked(accountValue.getUnlock_disk_staked());
        topAccount.setUnlockGasStaked(accountValue.getUnlock_gas_staked());
        topAccount.setUnusedVoteAmount(accountValue.getUnused_vote_amount());
        topAccount.setVoteStakedToken(accountValue.getVote_staked_token());
        topAccount.setZoneId(accountValue.getZone_id());
        topAccount.setTimestamp(accountValue.getCreated_time());

        topAccountService.saveOrUpdate(topAccount);
    }


    @Override
    public void onMessage(String address) {
        loadAccount(address);
    }
}
