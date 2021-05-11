package org.topnetwork.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.topnetwork.api.bean.CommonResult;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.resp.AccountResp;
import org.topnetwork.api.manager.AccountManager;

/**
 * @author CasonCai
 * @since 2021/5/6 7:07 下午
 **/
@RequestMapping("/top/account")
@RestController
public class AccountController {

    @Autowired
    AccountManager accountManager;

    @GetMapping("/getAccount")
    public CommonResult<AccountResp> getAccount(String address){
        AccountResp accountResp = accountManager.account(address);
        return CommonResult.success(accountResp);
    }

    @GetMapping("/getAccountList")
    public CommonResult<PageData<String>> getAccount(@RequestParam(name = "pageNum", required = false, defaultValue = "1") long pageNum,
                                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") long pageSize){
        PageData<String> accountAddressePage = accountManager.accountAddresses(pageNum, pageSize);
        return CommonResult.success(accountAddressePage);
    }
}
