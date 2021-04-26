package org.topnetwork.common.service.impl;

import org.topnetwork.common.entity.NodeInfo;
import org.topnetwork.common.dao.NodeInfoDao;
import org.topnetwork.common.service.NodeInfoService;
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
public class NodeInfoServiceImpl extends ServiceImpl<NodeInfoDao, NodeInfo> implements NodeInfoService {

}
