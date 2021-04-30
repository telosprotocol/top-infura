package org.topnetwork.common.dao;

import org.apache.ibatis.annotations.Select;
import org.topnetwork.common.entity.TopNodeIssuance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-27
 */
public interface TopNodeIssuanceDao extends BaseMapper<TopNodeIssuance> {
    @Select("select max(height) from top_node_issuance")
    Long maxHeight();
}
