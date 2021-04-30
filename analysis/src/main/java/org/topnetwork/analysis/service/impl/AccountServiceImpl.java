package org.topnetwork.analysis.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.topnetwork.analysis.service.AccountService;
import org.topnetwork.analysis.utils.ZoneRuleUtils;
import org.topnetwork.common.dao.TopAccountDao;
import org.topnetwork.common.entity.TopAccount;
import org.topnetwork.common.utils.TopUtils;
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
    private TopAccountDao topAccountDao;

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
    public TopAccount getAccOrSync(String address){
        TopAccount topAccount = queryAddress(address);
        if (!ObjectUtils.isEmpty(topAccount)) {
            return topAccount;
        }
        topAccount = new TopAccount();
        AccountResult account1 = TopGrpcClient.getInstance(ip, port).getAccount(address);
        int times = 10;
        while ((account1 == null || account1.getValue() == null) && times > 0) {
            account1 = TopGrpcClient.getInstance(ip, port).getAccount(address);
        }
        topAccount.setAddress(address);
        topAccount.setClusterId(account1.getValue().getCluster_id());
        topAccount.setNonce(account1.getValue().getNonce());
        topAccount.setShard(account1.getValue().getGroup_id());
        topAccount.setZoneId(account1.getValue().getZone_id());
        try {
            saveAccountAndContract(account1.getValue());
        } catch (Exception e) {
        }
        return topAccount;
    }

    private void saveAccount(AccountValue accountValue) {
        //如果不是合约账户就储存在账户中
//        TopGrpcClient grpcClient = TopGrpcClient.getInstance(ip, port);
//        AccountResult accountResult = grpcClient.getAccount(address);
        //查询数据库是否有这个address
        TopAccount topAccount = queryAddress(accountValue.getAccount_addr());
        if (ObjectUtils.isEmpty(topAccount)) {
            //如果为空就保存
            topAccount = new TopAccount();
            //交易数
            topAccount.setTxNum(BigInteger.ONE);
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
    }

    public TopAccount queryAddress(String address) {
        return null;
    }


}
