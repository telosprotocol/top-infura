package org.topnetwork.api.manager;

import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.resp.UnitBlockResp;
import org.topnetwork.common.entity.TopUnitBlock;

import java.util.List;

/**
 * @author CasonCai
 * @since 2021/4/30 4:21 下午
 **/
public interface UnitBlockManager {

    PageData<UnitBlockResp> getUnitBlocks(long pageNum, long pageSize);

    PageData<UnitBlockResp> getUnitBlocks(String address, long pageNum, long pageSize);

}
