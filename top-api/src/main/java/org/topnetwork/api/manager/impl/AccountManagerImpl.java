package org.topnetwork.api.manager.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.resp.AccountResp;
import org.topnetwork.api.manager.AccountManager;
import org.topnetwork.common.entity.TopAccount;
import org.topnetwork.common.service.TopAccountService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CasonCai
 * @since 2021/5/7 2:10 下午
 **/
@Component
public class AccountManagerImpl implements AccountManager {

    @Autowired
    TopAccountService accountService;

    @Override
    public AccountResp account(String address) {
        TopAccount topAccount = accountService.getByAddress(address);
        AccountResp accountResp = new AccountResp();
        BeanUtils.copyProperties(topAccount, accountResp);

        return accountResp;
    }

    @Override
    public PageData<String> accountAddresses(long pageNum, long pageSize) {
        Page<TopAccount> topAccountPage = accountService.getAccounts(pageNum, pageSize);
        List<String> accountRespList = topAccountPage.getRecords().stream()
                .map(TopAccount::getAddress)
                .collect(Collectors.toList());

        return PageData.createPage(pageNum, pageSize, topAccountPage.getPages(), topAccountPage.getTotal(), accountRespList);
    }
}
