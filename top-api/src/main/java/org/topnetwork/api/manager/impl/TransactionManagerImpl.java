package org.topnetwork.api.manager.impl;

import org.springframework.stereotype.Component;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.manager.TransactionManager;
import org.topnetwork.common.entity.TopTransaction;

/**
 * @author CasonCai
 * @since 2021/4/30 4:27 下午
 **/
@Component
public class TransactionManagerImpl implements TransactionManager {

    @Override
    public PageData<TopTransaction> getTransactions(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public PageData<TopTransaction> getTransactions(String address, int pageNum, int pageSize) {
        return null;
    }
}
