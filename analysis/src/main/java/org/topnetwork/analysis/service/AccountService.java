package org.topnetwork.analysis.service;

import org.topnetwork.common.entity.TopAccount;
import org.topnetwork.grpclib.pojo.account.AccountValue;

public interface AccountService {
    void saveAccountAndContract(AccountValue accountValue);

    /**
     * 从db获取账户信息
     * @param address 账户地址
     * @return 账户信息
     */
    TopAccount queryAddress(String address);

    TopAccount getAccOrSync(String address);
    void syncFormChain(String address);
}
