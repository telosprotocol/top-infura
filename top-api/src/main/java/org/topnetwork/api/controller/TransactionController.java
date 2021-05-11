package org.topnetwork.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.topnetwork.api.bean.CommonResult;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.resp.TransactionResp;
import org.topnetwork.api.manager.TransactionManager;
import org.topnetwork.common.entity.TopTransaction;

/**
 * @author CasonCai
 * @since 2021/4/30 10:18 上午
 **/
@RequestMapping("/top/transaction")
@RestController
public class TransactionController {

    @Autowired
    private TransactionManager transactionManager;

    @GetMapping("/getTransaction")
    public CommonResult<TransactionResp> getTransaction(String txHash){
        TransactionResp txResp = transactionManager.getTransaction(txHash);
        return CommonResult.success(txResp);
    }

    @GetMapping("/getTransactions")
    public CommonResult<PageData<TransactionResp>> getTransactions(
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") long pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") long pageSize
    ) {
        PageData<TransactionResp> pageData = transactionManager.getTransactions(address, pageNum, pageSize);
        return CommonResult.success(pageData);
    }

}
