package org.topnetwork.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.topnetwork.common.entity.TopTransaction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
public interface TopTransactionService extends IService<TopTransaction> {
    TopTransaction getTxByHash(String txHash);

    Page<TopTransaction> getTransactions(long pageNum, long pageSize);

    Page<TopTransaction> getTransactions(String address, long pageNum, long pageSize);

}
