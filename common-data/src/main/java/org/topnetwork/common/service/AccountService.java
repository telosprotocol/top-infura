package org.topnetwork.common.service;

import org.topnetwork.common.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账户表 服务类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
public interface AccountService extends IService<Account> {

    Account getByAddress(String address);

}
