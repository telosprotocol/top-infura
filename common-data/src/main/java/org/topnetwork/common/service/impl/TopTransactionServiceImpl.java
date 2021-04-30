package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
}
