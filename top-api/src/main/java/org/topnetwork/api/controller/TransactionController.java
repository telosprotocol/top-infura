package org.topnetwork.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.topnetwork.api.bean.CommonResult;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.manager.TransactionManager;
import org.topnetwork.common.entity.TopTransaction;

/**
 * @author CasonCai
 * @since 2021/4/30 10:18 上午
 **/
@RequestMapping("/transaction")
@RestController
public class TransactionController {

    @Autowired
    private TransactionManager transactionManager;

    @RequestMapping("/getTransactions")
    public CommonResult<PageData<TopTransaction>> getTransactions(
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        PageData<TopTransaction> pageData = transactionManager.getTransactions(address, pageNum, pageSize);
        return CommonResult.success(pageData);
    }

}
