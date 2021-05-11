package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.topnetwork.common.entity.TopNodeIssuance;
import org.topnetwork.common.dao.TopNodeIssuanceDao;
import org.topnetwork.common.service.TopNodeIssuanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

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

    @Override
    public List<TopNodeIssuance> getIssuance(String address, Long minTimesatmp, Long maxTimestamp) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("address", address);
        if(minTimesatmp != null){
            queryWrapper.ge("timestamp", minTimesatmp);
        }

        if(maxTimestamp != null){
            queryWrapper.le("timestamp", maxTimestamp);
        }
        return list(queryWrapper);
    }

}
