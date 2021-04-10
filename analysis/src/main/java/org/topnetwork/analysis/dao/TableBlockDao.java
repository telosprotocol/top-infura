package org.topnetwork.analysis.dao;

import org.apache.ibatis.annotations.*;
import org.topnetwork.grpclib.pojo.stream.Tableblock;

@Mapper
public interface TableBlockDao {

    int insert(Tableblock tableblock);

    Tableblock queryByHash(String hash);
}
