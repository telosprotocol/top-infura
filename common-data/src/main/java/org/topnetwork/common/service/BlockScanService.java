package org.topnetwork.common.service;

import org.topnetwork.common.entity.BlockScan;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-26
 */
public interface BlockScanService extends IService<BlockScan> {

    void plusScanHeight(String address, Long count);

    Long getScanHeight(String address);

}
