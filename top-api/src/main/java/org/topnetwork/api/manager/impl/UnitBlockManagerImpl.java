package org.topnetwork.api.manager.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.resp.UnitBlockResp;
import org.topnetwork.api.manager.UnitBlockManager;
import org.topnetwork.common.entity.TopUnitBlock;
import org.topnetwork.common.service.TopUnitBlockService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CasonCai
 * @since 2021/4/30 4:27 下午
 **/
@Component
public class UnitBlockManagerImpl implements UnitBlockManager {

    @Autowired
    TopUnitBlockService unitBlockService;

    @Override
    public PageData<UnitBlockResp> getUnitBlocks(long pageNum, long pageSize) {
        Page<TopUnitBlock> page = unitBlockService.getUnitBlocks(pageNum, pageSize);
        List<UnitBlockResp> unitBlockResps = page.getRecords().stream()
                .map(block -> {
                    UnitBlockResp unitBlockResp = new UnitBlockResp();
                    BeanUtils.copyProperties(block, unitBlockResp);
                    return unitBlockResp;
                })
                .collect(Collectors.toList());

        return PageData.createPage(pageNum, pageSize, page.getPages(), page.getTotal(), unitBlockResps);
    }

    @Override
    public PageData<UnitBlockResp> getUnitBlocks(String address, long pageNum, long pageSize) {
        Page<TopUnitBlock> page = unitBlockService.getUnitBlocks(address,pageNum, pageSize);
        List<UnitBlockResp> unitBlockResps = page.getRecords().stream()
                .map(block -> {
                    UnitBlockResp unitBlockResp = new UnitBlockResp();
                    BeanUtils.copyProperties(block, unitBlockResp);
                    return unitBlockResp;
                })
                .collect(Collectors.toList());

        return PageData.createPage(pageNum, pageSize, page.getPages(), page.getTotal(), unitBlockResps);

    }

}
