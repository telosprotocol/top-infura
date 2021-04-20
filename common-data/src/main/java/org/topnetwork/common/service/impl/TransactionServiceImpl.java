package org.topnetwork.common.service.impl;

import org.topnetwork.common.entity.Transaction;
import org.topnetwork.common.dao.TransactionDao;
import org.topnetwork.common.service.TransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionDao, Transaction> implements TransactionService {

}
