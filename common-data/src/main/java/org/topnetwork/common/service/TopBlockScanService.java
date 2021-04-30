package org.topnetwork.common.service;

import org.topnetwork.common.entity.TopBlockScan;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-26
 */
public interface TopBlockScanService extends IService<TopBlockScan> {

    void plusScanHeight(String address, Long count);

    Long getScanHeight(String address);

}
