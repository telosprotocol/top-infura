package org.topnetwork.api.manager;

import org.topnetwork.api.bean.resp.NodeInfoResp;
import org.topnetwork.api.bean.resp.NodeRewardDetailResp;
import org.topnetwork.api.bean.resp.VoteUsedResp;
import org.topnetwork.api.bean.resp.VoterDividend;

import java.util.List;

/**
 * @author CasonCai
 * @since 2021/5/7 2:38 下午
 **/
public interface NodeManager {

    List<NodeInfoResp> allNode();

    NodeInfoResp getNode(String address);

    List<NodeRewardDetailResp> getNodeRewardDetail(String address, String type,Long minTimesatmp, Long maxTimestamp);

    VoteUsedResp voteUsed(String address);

    VoterDividend voterDividend(String address);

}
