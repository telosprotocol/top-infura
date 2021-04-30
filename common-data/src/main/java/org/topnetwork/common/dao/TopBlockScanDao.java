package org.topnetwork.common.dao;

import org.apache.ibatis.annotations.Param;
import org.topnetwork.common.entity.TopBlockScan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-26
 */
public interface TopBlockScanDao extends BaseMapper<TopBlockScan> {

    boolean plusScanHeight(@Param("address")String address, @Param("count") Long count);

}
