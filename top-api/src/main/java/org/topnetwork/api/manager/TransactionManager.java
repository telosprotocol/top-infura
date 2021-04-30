package org.topnetwork.api.manager;

import org.topnetwork.api.bean.PageData;
import org.topnetwork.common.dao.TopTransactionDao;
import org.topnetwork.common.entity.TopTransaction;

import java.util.List;

/**
 * @author CasonCai
 * @since 2021/4/30 4:19 下午
 **/
public interface TransactionManager {

    PageData<TopTransaction> getTransactions(int pageNum, int pageSize);

    PageData<TopTransaction> getTransactions(String address, int pageNum, int pageSize);

}
