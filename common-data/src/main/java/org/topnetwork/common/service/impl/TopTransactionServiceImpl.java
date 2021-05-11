package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.topnetwork.common.entity.TopTransaction;
import org.topnetwork.common.dao.TopTransactionDao;
import org.topnetwork.common.service.TopTransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Service
public class TopTransactionServiceImpl extends ServiceImpl<TopTransactionDao, TopTransaction> implements TopTransactionService {

    @Override
    public TopTransaction getTxByHash(String txHash) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("hash", txHash);

        return getOne(queryWrapper);
    }

    @Override
    public Page<TopTransaction> getTransactions(long pageNum, long pageSize) {
        Page<TopTransaction> page = new Page(pageNum, pageSize);

        QueryWrapper queryWrapper = new QueryWrapper();
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<TopTransaction> getTransactions(String address, long pageNum, long pageSize) {
        Page<TopTransaction> page = new Page(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("from", address);
        return baseMapper.selectPage(page, queryWrapper);
    }


}
