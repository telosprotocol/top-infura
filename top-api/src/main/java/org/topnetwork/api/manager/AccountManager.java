package org.topnetwork.api.manager;

import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.resp.AccountResp;

import java.util.List;

/**
 * @author CasonCai
 * @since 2021/5/7 2:05 下午
 **/
public interface AccountManager {

    AccountResp account(String address);

    PageData<String> accountAddresses(long pageNum, long pageSize);
}
