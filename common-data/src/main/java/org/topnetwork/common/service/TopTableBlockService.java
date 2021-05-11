package org.topnetwork.common.service;

import org.topnetwork.common.entity.TopTableBlock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * table块表 服务类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
public interface TopTableBlockService extends IService<TopTableBlock> {

    void updateLatestHeight(String address, Long height);

    Long getLatestHeight(String address);

    List<Long> getAllLatestHeight();
}
