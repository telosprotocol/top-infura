package org.topnetwork.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.topnetwork.common.entity.TopUnitBlock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * unitblock表 服务类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
public interface TopUnitBlockService extends IService<TopUnitBlock> {

    Page<TopUnitBlock> getUnitBlocks(long pageNum, long pageSize);

    Page<TopUnitBlock> getUnitBlocks(String address, long pageNum, long pageSize);


    TopUnitBlock getUnitBlock(String address, long height);

    TopUnitBlock getUnitBlock(String hash);
}
