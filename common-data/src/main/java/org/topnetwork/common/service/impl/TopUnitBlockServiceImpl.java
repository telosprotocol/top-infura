package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.topnetwork.common.entity.TopUnitBlock;
import org.topnetwork.common.dao.TopUnitBlockDao;
import org.topnetwork.common.service.TopUnitBlockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * unitblock表 服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Service
public class TopUnitBlockServiceImpl extends ServiceImpl<TopUnitBlockDao, TopUnitBlock> implements TopUnitBlockService {


    @Override
    public Page<TopUnitBlock> getUnitBlocks(long pageNum, long pageSize) {
        Page<TopUnitBlock> page = new Page<>(pageNum, pageSize);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("timestamp");

        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<TopUnitBlock> getUnitBlocks(String address, long pageNum, long pageSize) {
        Page<TopUnitBlock> page = new Page<>(pageNum, pageSize);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("owner", address);
        queryWrapper.orderByDesc("timestamp");

        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public TopUnitBlock getUnitBlock(String address, long height) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("owner", address);
        queryWrapper.eq("height", height);
        queryWrapper.orderByDesc("timestamp");

        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public TopUnitBlock getUnitBlock(String hash) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("hash", hash);

        return baseMapper.selectOne(queryWrapper);
    }
}
