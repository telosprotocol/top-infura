package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.util.ObjectUtils;
import org.topnetwork.common.entity.TopNodeInfo;
import org.topnetwork.common.dao.TopNodeInfoDao;
import org.topnetwork.common.service.TopNodeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Service
public class TopNodeInfoServiceImpl extends ServiceImpl<TopNodeInfoDao, TopNodeInfo> implements TopNodeInfoService {

    @Override
    public Set<String> getAllNodeAddress() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("address");

        List<String> addressList = listObjs(queryWrapper, Object::toString);
        return new HashSet<>(addressList);
    }

    @Override
    public boolean updateNodeInfo(TopNodeInfo topNodeInfo) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("auditor_credit", topNodeInfo.getAuditorCredit());
        updateWrapper.set("auditor_stake", topNodeInfo.getAuditorStake());
        updateWrapper.set("validator_credit", topNodeInfo.getValidatorCredit());
        updateWrapper.set("validator_stake", topNodeInfo.getValidatorStake());
        updateWrapper.set("dividen_ratio", topNodeInfo.getDividenRatio());
        updateWrapper.set("network_id", topNodeInfo.getNetworkId());
        updateWrapper.set("node_deposit", topNodeInfo.getNodeDeposit());
        updateWrapper.set("vote_amount", topNodeInfo.getVoteAmount());
        updateWrapper.set("rec_stake", topNodeInfo.getRecStake());
        updateWrapper.set("zec_stake", topNodeInfo.getZecStake());
        updateWrapper.set("version", topNodeInfo.getVersion());
        updateWrapper.set("name", topNodeInfo.getName());

        updateWrapper.eq("address", topNodeInfo.getAddress());
        updateWrapper.eq("type", topNodeInfo.getType());

        return update(updateWrapper);
    }

    public boolean udpateNodeRewardInfo(TopNodeInfo topNodeInfo){
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("last_claim_timestamp", topNodeInfo.getLastClaimTimestamp());
        updateWrapper.set("last_issue_timestamp", topNodeInfo.getLastIssueTimestamp());
        updateWrapper.set("accumulated_amount", topNodeInfo.getAccumulatedAmount());
        updateWrapper.set("unclaim_amount", topNodeInfo.getUnclaimAmount());

        updateWrapper.eq("address", topNodeInfo.getAddress());

        return update(updateWrapper);
    }



    @Override
    public List<TopNodeInfo> getElectedNodeByTypes(Collection<String> types) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("type", types);
        queryWrapper.eq("elected", true);

        return list(queryWrapper);
    }

    @Override
    public boolean electNode(String address, String type,BigInteger version, LocalDateTime lastElectedTime, Long lastElectedBlockHeight) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("last_elected_time", lastElectedTime);
        updateWrapper.set("last_elected_block_height", lastElectedBlockHeight);
        updateWrapper.set("version", version);
        updateWrapper.set("elected", true);

        updateWrapper.eq("address", address);
        updateWrapper.eq("type", type);
        return update(updateWrapper);
    }


    @Override
    public void loseElectNode(Collection<TopNodeInfo> topNodeInfos) {
        if(ObjectUtils.isEmpty(topNodeInfos)){
            return;
        }

        List<Long> ids = topNodeInfos.stream()
                .map(TopNodeInfo::getId)
                .collect(Collectors.toList());

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.in("id",ids);
        updateWrapper.set("elected", true);
        update(updateWrapper);
    }
}
