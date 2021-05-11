package org.topnetwork.common.service.impl;

import org.topnetwork.common.entity.TopElectionBlock;
import org.topnetwork.common.dao.TopElectionBlockDao;
import org.topnetwork.common.service.TopElectionBlockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 选举块信息 服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-05-10
 */
@Service
public class TopElectionBlockServiceImpl extends ServiceImpl<TopElectionBlockDao, TopElectionBlock> implements TopElectionBlockService {

}
