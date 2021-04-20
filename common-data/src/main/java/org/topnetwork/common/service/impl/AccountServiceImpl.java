package org.topnetwork.common.service.impl;

import org.topnetwork.common.entity.Account;
import org.topnetwork.common.dao.AccountDao;
import org.topnetwork.common.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {

}
