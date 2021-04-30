package org.topnetwork.api.manager.impl;

import org.springframework.stereotype.Component;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.manager.UnitBlockManager;
import org.topnetwork.common.entity.TopUnitBlock;

import java.util.List;

/**
 * @author CasonCai
 * @since 2021/4/30 4:27 下午
 **/
@Component
public class UnitBlockManagerImpl implements UnitBlockManager {
    @Override
    public List<TopUnitBlock> getTopUnitBlock(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public PageData<TopUnitBlock> getTopUnitBlock(String address, int pageNum, int pageSize) {
        return null;
    }
}
