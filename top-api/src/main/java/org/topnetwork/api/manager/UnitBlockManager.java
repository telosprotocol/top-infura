package org.topnetwork.api.manager;

import org.topnetwork.api.bean.PageData;
import org.topnetwork.common.entity.TopUnitBlock;

import java.util.List;

/**
 * @author CasonCai
 * @since 2021/4/30 4:21 下午
 **/
public interface UnitBlockManager {

    List<TopUnitBlock> getTopUnitBlock(int pageNum, int pageSize);

    PageData<TopUnitBlock> getTopUnitBlock(String address, int pageNum, int pageSize);

}
