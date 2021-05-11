package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.util.ObjectUtils;
import org.topnetwork.common.entity.TopNodeElection;
import org.topnetwork.common.dao.TopNodeElectionDao;
import org.topnetwork.common.entity.TopNodeInfo;
import org.topnetwork.common.service.TopNodeElectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-05-08
 */
@Service
public class TopNodeElectionServiceImpl extends ServiceImpl<TopNodeElectionDao, TopNodeElection> implements TopNodeElectionService {


    @Override
    public List<TopNodeElection> getElectedNodes(List<String> types) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("elected", true);
        queryWrapper.in("type", types);
        return list(queryWrapper);
    }


    @Override
    public boolean electeNode(String address, String nodeType, LocalDateTime electedTime, Long electedBlockHeight) {

        TopNodeElection nodeElection = new TopNodeElection();
        nodeElection.setAddress(address);
        nodeElection.setType(nodeType);
        nodeElection.setElected(true);
        nodeElection.setLastElectedTime(electedTime);
        nodeElection.setLastElectedBlockHeight(electedBlockHeight);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("address", address);
        queryWrapper.eq("type", nodeType);

        TopNodeElection exitNode = baseMapper.selectOne(queryWrapper);
        if (exitNode != null) {
            return update(nodeElection, queryWrapper);
        } else {
            return save(nodeElection);
        }
    }

    @Override
    public void loseElectNode(Collection<TopNodeElection> topNodeInfos) {
        if (ObjectUtils.isEmpty(topNodeInfos)) {
            return;
        }

        List<Long> ids = topNodeInfos.stream()
                .map(TopNodeElection::getId)
                .collect(Collectors.toList());

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.in("id", ids);
        updateWrapper.set("elected", false);
        update(updateWrapper);
    }
}
