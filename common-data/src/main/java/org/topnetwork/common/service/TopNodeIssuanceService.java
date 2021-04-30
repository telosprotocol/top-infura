package org.topnetwork.common.service;

import org.topnetwork.common.entity.TopNodeIssuance;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-27
 */
public interface TopNodeIssuanceService extends IService<TopNodeIssuance> {

    Long getCurrentHeight();

}
