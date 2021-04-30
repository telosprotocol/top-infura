package org.topnetwork.analysis.block;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.topnetwork.common.constant.TopConstants;
import org.topnetwork.common.entity.TopNodeInfo;
import org.topnetwork.common.enums.AddressEnum;
import org.topnetwork.common.service.TopNodeInfoService;
import org.topnetwork.common.utils.TopUtils;
import org.topnetwork.grpclib.pojo.election.Elect_nodes;
import org.topnetwork.grpclib.pojo.election.ElectionBlockResult;
import org.topnetwork.grpclib.pojo.node.NodeInfoResult;
import org.topnetwork.grpclib.pojo.node.NodeResult;
import org.topnetwork.grpclib.pojo.node.NodeRewardResult;
import org.topnetwork.grpclib.pojo.node.NodeRewardValue;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author CasonCai
 * @since 2021/4/28 7:24 下午
 **/
@Slf4j
@Component
public class NodeInfoLoader {

    @Autowired
    private TopGrpcClient grpcClient;

    @Autowired
    private TopNodeInfoService topNodeInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 同步所有节点信息
     */
    public void loadAllNodeInfo(){
        NodeResult nodeResult = grpcClient.getNodeInfo();
        Map<String, NodeInfoResult> valueMap = nodeResult.getValue();
        List<TopNodeInfo> topNodeInfos = valueMap.values().stream()
                .map(this::createNodeInfo)
                .collect(Collectors.toList());

        Set<String> existNodeAddress = topNodeInfoService.getAllNodeAddress();

        for (TopNodeInfo topNodeInfo : topNodeInfos) {
            if(existNodeAddress.contains(topNodeInfo.getAddress())){
                topNodeInfoService.updateNodeInfo(topNodeInfo);
            }else{
                topNodeInfoService.save(topNodeInfo);
            }
        }

        updateNodeRewardInfo();

    }

    /**
     * 同步节点奖励信息
     */
    private void updateNodeRewardInfo(){
        NodeRewardResult rewardResult = grpcClient.queryNodeReward();
        Map<String, NodeRewardValue> rewardValueMap = rewardResult.getValue();
        for (Map.Entry<String, NodeRewardValue> entry : rewardValueMap.entrySet()) {
            String address = entry.getKey();
            NodeRewardValue value = entry.getValue();
            TopNodeInfo topNodeReward = createNodeRewardInfo(address, value);
            topNodeInfoService.udpateNodeRewardInfo(topNodeReward);
        }
    }


    /**
     * 同步所有选举块信息
     */
    public void loadElectInfo() {
        loadElectInfo(currentElectBlockHeight() + 1);
    }

    /**
     * 同步选举信息
     * @param height 由该高度开始同步
     */
    public void loadElectInfo(Long height){
        //查auditor 会查出auditor、validator两种节点
        queryAndUpdateElectNode(AddressEnum.auditor, height);
        queryAndUpdateElectNode(AddressEnum.edge, height );
        queryAndUpdateElectNode(AddressEnum.archive, height);
    }

    /**
     * 查询并保存新增的选举节点
     * @param addressEnum
     * @param startHeight
     */
    private void queryAndUpdateElectNode(AddressEnum addressEnum, Long startHeight) {

        Long nextHeight = startHeight;

        while (true) {
            try {

                List<String> queryNodeTypes = new ArrayList();
                //auditor会查出auditor、validate两种节点
                if(addressEnum == AddressEnum.auditor){
                    queryNodeTypes.add(AddressEnum.auditor.name());
                    queryNodeTypes.add(AddressEnum.validator.name());
                }else{
                    queryNodeTypes.add(addressEnum.name());
                }

                List<TopNodeInfo> electedNodeList =  topNodeInfoService.getElectedNodeByTypes(queryNodeTypes);

                /**
                 * 上一个区块当选的节点map
                 * key=nodeAddress&nodeType
                 */
                Map<String, TopNodeInfo> electedNodeMap = new HashMap<>();
                if(electedNodeList != null){
                    for (TopNodeInfo topNodeInfo : electedNodeList) {
                        String key = topNodeInfo.getAddress() + "&" + topNodeInfo.getType();
                        electedNodeMap.put(key, topNodeInfo);
                    }
                }

                Long currentHeight = nextHeight;

                ElectionBlockResult result = grpcClient.getElectionBlock(addressEnum.getAddress(), currentHeight);

                if (result == null || result.getValue() == null) {
                    break;
                }

                Long timestamp = result.getValue().getTimestamp();

                //当前当选的节点
                List<Elect_nodes> nodeList = result.getValue().getBody().getElect_transaction().getElect_nodes();

                if(ObjectUtils.isEmpty(nodeList)){
                    break;
                }

                //转成nodeInfo
                List<TopNodeInfo> newElectNodeList = nodeList.stream()
                        .map(currentElectNode -> {
                            TopNodeInfo topNodeInfo = new TopNodeInfo();
                            topNodeInfo.setAddress(currentElectNode.getAccount());
                            topNodeInfo.setLastElectedTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault()));
                            topNodeInfo.setLastElectedBlockHeight(currentHeight);
                            topNodeInfo.setType(currentElectNode.getNode_type());
                            topNodeInfo.setVersion(currentElectNode.getVersion());
                            return topNodeInfo;
                        })
                        //过滤掉上次已经当选的节点
                        .filter(nodeInfo -> {
                            String account = nodeInfo.getAddress();
                            String nodeType = nodeInfo.getType();
                            String electNodeName = account + "&" + nodeType;
                            //删除上次当选的节点,上次已当选的不用更新了
                            return electedNodeMap.remove(electNodeName) == null;
                        })
                        .collect(Collectors.toList());

                for (TopNodeInfo topNodeInfo : newElectNodeList) {
                    topNodeInfoService.electNode(topNodeInfo.getAddress(),
                            topNodeInfo.getType(),
                            topNodeInfo.getVersion(),
                            topNodeInfo.getLastElectedTime(),
                            topNodeInfo.getLastElectedBlockHeight());
                }

                //lastElectNodeList中剩下的是未当选的节点
                topNodeInfoService.loseElectNode(electedNodeMap.values());

                setElectBlockHeight(addressEnum.toString(), nextHeight);
                nextHeight++;
            } catch (Exception e) {
                log.error("queryNewElectNode error, nodeType=" + addressEnum.toString() + ", height=" + nextHeight, e);
            }
        }
    }

    /**
     * 当前扫到的选举块高度
     * @return
     */
    private Long currentElectBlockHeight(){
        Object value = redisTemplate.opsForValue().get(TopConstants.ELECTION_BLOCK_HEIGHT);
        if(value == null){
            return 0L;
        }
        return Long.parseLong(value.toString());
    }



    /**
     * 设置下一个选举块高度
     * @param height
     */
    private void setElectBlockHeight(String nodeType,Long height){
        redisTemplate.opsForValue().set(TopConstants.ELECTION_BLOCK_HEIGHT + "_" + nodeType, height);
    }

    /**
     * 构建NodeInfo对象
     * @param nodeInfoResult
     * @return
     */
    private TopNodeInfo createNodeInfo(NodeInfoResult nodeInfoResult){
        TopNodeInfo topNodeInfo = new TopNodeInfo();
        topNodeInfo.setName(nodeInfoResult.getNodename());
        topNodeInfo.setAddress(nodeInfoResult.getAccount_addr());
        topNodeInfo.setType(nodeInfoResult.getRegistered_node_type());
        topNodeInfo.setNodeDeposit(nodeInfoResult.getNode_deposit());
        topNodeInfo.setAuditorCredit(new BigDecimal(nodeInfoResult.getAuditor_credit()));
        topNodeInfo.setAuditorStake(nodeInfoResult.getAuditor_stake());
        topNodeInfo.setValidatorCredit(new BigDecimal(nodeInfoResult.getValidator_credit()));
        topNodeInfo.setValidatorStake(nodeInfoResult.getValidator_stake());
        topNodeInfo.setZecStake(nodeInfoResult.getZec_stake());
        topNodeInfo.setRecStake(nodeInfoResult.getRec_stake());
        topNodeInfo.setNetworkId(nodeInfoResult.getNetwork_id());

        return topNodeInfo;
    }


    /**
     * 构建NodeReward对象
     * @param address
     * @param value
     * @return
     */
    private TopNodeInfo createNodeRewardInfo(String address, NodeRewardValue value){
        BigInteger accumulated = value.getAccumulated();
        BigInteger accumulatedDecimals = value.getAccumulated_decimals();
        BigInteger unclaimed = value.getUnclaimed();
        BigInteger unclaimedDecimals = value.getUnclaimed_decimals();
        BigInteger lastClaimTime = value.getLast_claim_time();
        BigInteger lastIssueTime = value.getIssue_time();

        TopNodeInfo topNodeRewardInfo = new TopNodeInfo();
        topNodeRewardInfo.setAddress(address);

        BigInteger fullAccumulated = accumulated.multiply(TopConstants.TOP_ACCURACY.toBigInteger()).add(accumulatedDecimals);
        BigInteger fullUnclaimed = unclaimed.multiply(TopConstants.TOP_ACCURACY.toBigInteger()).add(unclaimedDecimals);
        topNodeRewardInfo.setAccumulatedAmount(fullAccumulated);
        topNodeRewardInfo.setUnclaimAmount(fullUnclaimed);
        if(lastClaimTime != null && lastClaimTime.compareTo(BigInteger.ZERO) > 0){
            topNodeRewardInfo.setLastClaimTimestamp(TopUtils.convertTimerHeight2Timestamp(lastClaimTime.longValue()));
        }
        if(lastIssueTime != null && lastIssueTime.compareTo(BigInteger.ZERO) > 0){
            topNodeRewardInfo.setLastIssueTimestamp(TopUtils.convertTimerHeight2Timestamp(lastIssueTime.longValue()));
        }
        return topNodeRewardInfo;
    }
}
