package org.topnetwork.analysis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.topnetwork.analysis.bean.Transaction;

@Mapper
public interface TransactionDao {

    int insert(Transaction transaction);

    Transaction queryByHash(String hash);
}
