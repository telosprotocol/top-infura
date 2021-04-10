package org.topnetwork.analysis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.topnetwork.analysis.bean.UnitBlock;

@Mapper
public interface UnitBlockDao {

    int insert(UnitBlock unitBlock);

    UnitBlock queryByHash(String hash);
}
