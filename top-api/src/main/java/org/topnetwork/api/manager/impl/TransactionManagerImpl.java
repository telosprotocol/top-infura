package org.topnetwork.api.manager.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.resp.TransactionResp;
import org.topnetwork.api.manager.TransactionManager;
import org.topnetwork.common.entity.TopTransaction;
import org.topnetwork.common.service.TopTransactionService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CasonCai
 * @since 2021/4/30 4:27 下午
 **/
@Component
public class TransactionManagerImpl implements TransactionManager {

    @Autowired
    TopTransactionService transactionService;


    @Override
    public PageData<TransactionResp> getTransactions(long pageNum, long pageSize) {
        Page<TopTransaction>page = transactionService.getTransactions(pageNum, pageSize);
        List<TransactionResp> txRespList = page.getRecords().stream()
                .map(tx->{
                    TransactionResp txResp = new TransactionResp();
                    BeanUtils.copyProperties(tx, txResp);
                    return txResp;
                })
                .collect(Collectors.toList());

        return new PageData<TransactionResp>(pageNum, pageSize, page.getPages(), page.getTotal(), txRespList);
    }

    @Override
    public PageData<TransactionResp> getTransactions(String address, long pageNum, long pageSize) {
        Page<TopTransaction>page = null;
        if(ObjectUtils.isEmpty(address)){
            page = transactionService.getTransactions(pageNum, pageSize);
        }else{
            page = transactionService.getTransactions(address,pageNum, pageSize);
        }
        List<TransactionResp> txRespList = page.getRecords().stream()
                .map(tx->{
                    TransactionResp txResp = new TransactionResp();
                    BeanUtils.copyProperties(tx, txResp);
                    return txResp;
                })
                .collect(Collectors.toList());

        return new PageData<TransactionResp>(pageNum, pageSize, page.getPages(), page.getTotal(), txRespList);
    }

    @Override
    public TransactionResp getTransaction(String txHash) {
        TopTransaction transaction = transactionService.getTxByHash(txHash);

        TransactionResp resp = new TransactionResp();
        BeanUtils.copyProperties(transaction , resp);
        return resp;
    }
}
