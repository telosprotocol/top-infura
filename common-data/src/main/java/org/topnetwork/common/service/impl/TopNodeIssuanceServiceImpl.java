package org.topnetwork.common.service.impl;

import org.topnetwork.common.entity.TopNodeIssuance;
import org.topnetwork.common.dao.TopNodeIssuanceDao;
import org.topnetwork.common.service.TopNodeIssuanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-27
 */
@Service
public class TopNodeIssuanceServiceImpl extends ServiceImpl<TopNodeIssuanceDao, TopNodeIssuance> implements TopNodeIssuanceService {

    @Override
    public Long getCurrentHeight() {
        Long maxHeight = baseMapper.maxHeight();
        if(maxHeight == null){
            return 0L;
        }else{
            return maxHeight;
        }
    }
}
