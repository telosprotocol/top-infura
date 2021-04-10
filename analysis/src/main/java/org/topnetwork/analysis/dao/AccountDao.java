package org.topnetwork.analysis.dao;

import org.apache.ibatis.annotations.*;
import org.topnetwork.analysis.bean.Account;

@Mapper
public interface AccountDao {

    int insert(Account account);

    Account queryByAddress(String address);
}
