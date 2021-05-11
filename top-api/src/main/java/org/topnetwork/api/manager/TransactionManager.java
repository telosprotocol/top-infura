package org.topnetwork.api.manager;

import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.resp.TransactionResp;
import org.topnetwork.common.entity.TopTransaction;

/**
 * @author CasonCai
 * @since 2021/4/30 4:19 下午
 **/
public interface TransactionManager {

    PageData<TransactionResp> getTransactions(long pageNum, long pageSize);

    PageData<TransactionResp> getTransactions(String address, long pageNum, long pageSize);

    TransactionResp getTransaction(String txHash);

}
