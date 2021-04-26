package org.topnetwork.analysis.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.topnetwork.analysis.utils.TopUtils;
import org.topnetwork.analysis.service.AccountService;
import org.topnetwork.analysis.utils.ZoneRuleUtils;
import org.topnetwork.common.dao.AccountDao;
import org.topnetwork.common.entity.Account;
import org.topnetwork.grpclib.pojo.account.AccountResult;
import org.topnetwork.grpclib.pojo.account.AccountValue;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.math.BigInteger;

public class AccountServiceImpl implements AccountService {

    @Value("${top.grpcip}")
    private String ip;
    @Value("${top.grpcport}")
    private int port;

    @SuppressWarnings("unused")
    private AccountDao accountDao;

    @Override
    public void syncFormChain(String address) {
        AccountResult account1 = TopGrpcClient.getInstance(ip, port).getAccount(address);
        if (!ObjectUtils.isEmpty(account1) && !ObjectUtils.isEmpty(account1.getValue())) {
            saveAccountAndContract(account1.getValue());
        }
    }

    @Override
    @Transactional
    public void saveAccountAndContract(AccountValue accountValue) {
        saveAccount(accountValue);
    }

    @Override
    public Account getAccOrSync(String address){
        Account account = queryAddress(address);
        if (!ObjectUtils.isEmpty(account)) {
            return account;
        }
        account = new Account();
        AccountResult account1 = TopGrpcClient.getInstance(ip, port).getAccount(address);
        int times = 10;
        while ((account1 == null || account1.getValue() == null) && times > 0) {
            account1 = TopGrpcClient.getInstance(ip, port).getAccount(address);
        }
        account.setAddress(address);
        account.setClusterId(account1.getValue().getCluster_id());
        account.setNonce(account1.getValue().getNonce());
        account.setShard(account1.getValue().getGroup_id());
        account.setZoneId(account1.getValue().getZone_id());
        try {
            saveAccountAndContract(account1.getValue());
        } catch (Exception e) {
        }
        return account;
    }

    private void saveAccount(AccountValue accountValue) {
        //如果不是合约账户就储存在账户中
//        TopGrpcClient grpcClient = TopGrpcClient.getInstance(ip, port);
//        AccountResult accountResult = grpcClient.getAccount(address);
        //查询数据库是否有这个address
        Account account = queryAddress(accountValue.getAccount_addr());
        if (ObjectUtils.isEmpty(account)) {
            //如果为空就保存
            account = new Account();
            //交易数
            account.setTxNum(BigInteger.ONE);
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
    }

    public Account queryAddress(String address) {
        return null;
    }


}
