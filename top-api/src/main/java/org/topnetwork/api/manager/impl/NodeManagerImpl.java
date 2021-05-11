package org.topnetwork.api.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.topnetwork.api.bean.resp.NodeInfoResp;
import org.topnetwork.api.bean.resp.NodeRewardDetailResp;
import org.topnetwork.api.bean.resp.VoteUsedResp;
import org.topnetwork.api.bean.resp.VoterDividend;
import org.topnetwork.api.manager.NodeManager;
import org.topnetwork.common.entity.TopNodeIssuance;
import org.topnetwork.common.service.TopNodeIssuanceService;
import org.topnetwork.grpclib.enums.NodeType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CasonCai
 * @since 2021/5/10 9:39 上午
 **/
@Component
public class NodeManagerImpl implements NodeManager {
    @Autowired
    private TopNodeIssuanceService nodeIssuanceService;


    @Override
    public List<NodeInfoResp> allNode() {
        return null;
    }

    @Override
    public NodeInfoResp getNode(String address) {
        return null;
    }

    @Override
    public List<NodeRewardDetailResp> getNodeRewardDetail(String address, String type, Long minTimesatmp, Long maxTimestamp) {
        List<TopNodeIssuance> nodeIssuanceList = nodeIssuanceService.getIssuance(address,minTimesatmp, maxTimestamp);

        List<NodeRewardDetailResp> rewardDetailResps = new ArrayList<>();
        for (TopNodeIssuance topNodeIssuance : nodeIssuanceList) {
            NodeRewardDetailResp rewardDetail = buildRewardDetail(topNodeIssuance, type);
            rewardDetailResps.add(rewardDetail);
        }

        return rewardDetailResps;
    }

    @Override
    public VoteUsedResp voteUsed(String address) {




        return null;
    }

    @Override
    public VoterDividend voterDividend(String address) {
        return null;
    }



    private NodeRewardDetailResp buildRewardDetail(TopNodeIssuance nodeIssuance, String nodeTypeName){
        NodeType nodeType = NodeType.valueOf(nodeTypeName);

        Long timesatmp = nodeIssuance.getTimestamp();
        BigInteger reward = BigInteger.ZERO;

        switch (nodeType){
            case auditor:
                reward = nodeIssuance.getAuditorReward();
                break;
            case validator:
                reward = nodeIssuance.getValidatorReward();
                break;
        }

        return new NodeRewardDetailResp(timesatmp, reward);
    }
}
