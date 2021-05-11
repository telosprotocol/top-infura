package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.topnetwork.common.entity.TopAccount;
import org.topnetwork.common.dao.TopAccountDao;
import org.topnetwork.common.service.TopAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Service
public class TopAccountServiceImpl extends ServiceImpl<TopAccountDao, TopAccount> implements TopAccountService {

    @Override
    public TopAccount getByAddress(String address) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("address", address);
        return getOne(wrapper);
    }

    @Override
    public Page<TopAccount> getAccounts(long pageNum, long pageSize) {
        QueryWrapper wrapper = new QueryWrapper();

        Page page = new Page(pageNum, pageSize);


        return baseMapper.selectPage(page, wrapper);
    }
}
