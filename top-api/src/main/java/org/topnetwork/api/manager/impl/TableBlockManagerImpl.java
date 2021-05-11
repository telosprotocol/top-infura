package org.topnetwork.api.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.topnetwork.api.manager.TableBlockManager;
import org.topnetwork.common.entity.TopTableBlock;
import org.topnetwork.common.service.TopTableBlockService;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author CasonCai
 * @since 2021/4/30 4:05 下午
 **/
@Component
public class TableBlockManagerImpl implements TableBlockManager {
    @Autowired
    private TopTableBlockService tableBlockService;

    @Override
    public List<Long> getLatestTableBlockHeight() {


        return null;
    }

    @Override
    public TopTableBlock getTableBlock(String address, Long height) {
        return null;
    }
}
