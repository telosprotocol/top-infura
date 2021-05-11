package org.topnetwork.analysis.block;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.topnetwork.common.constant.TopConstants;
import org.topnetwork.common.entity.TopElectionBlock;
import org.topnetwork.common.entity.TopNodeElection;
import org.topnetwork.common.entity.TopNodeInfo;
import org.topnetwork.common.enums.AddressEnum;
import org.topnetwork.common.service.TopElectionBlockService;
import org.topnetwork.common.service.TopNodeElectionService;
import org.topnetwork.common.service.TopNodeInfoService;
import org.topnetwork.common.utils.TopUtils;
import org.topnetwork.grpclib.pojo.election.Elect_nodes;
import org.topnetwork.grpclib.pojo.election.ElectionBlockResult;
import org.topnetwork.grpclib.pojo.election.Value;
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
    private TopNodeElectionService nodeElectionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TopElectionBlockService electionBlockService;

    /**
     * 同步所有节点信息
     */
    public void loadAllNodeInfo() {
        NodeResult nodeResult = grpcClient.getNodeInfo();
        Map<String, NodeInfoResult> valueMap = nodeResult.getValue();
        List<TopNodeInfo> topNodeInfos = valueMap.values().stream()
                .map(this::createNodeInfo)
                .collect(Collectors.toList());

        Set<String> existNodeAddress = topNodeInfoService.getAllNodeAddress();

        for (TopNodeInfo topNodeInfo : topNodeInfos) {
            if (existNodeAddress.contains(topNodeInfo.getAddress())) {
                topNodeInfoService.updateNodeInfo(topNodeInfo);
            } else {
                topNodeInfoService.save(topNodeInfo);
            }
        }

        updateNodeRewardInfo();

    }

    /**
     * 同步节点奖励信息
     */
    private void updateNodeRewardInfo() {
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
        loadElectInfo(null);
    }

    /**
     * 同步选举信息
     *
     * @param height 由该高度开始同步
     */
    public void loadElectInfo(Long height) {
        if (height == null) {
            //查auditor 会查出auditor、validator两种节点
            queryAndUpdateElectNode(AddressEnum.auditor, currentElectBlockHeight(AddressEnum.auditor.toString()));
            queryAndUpdateElectNode(AddressEnum.edge, currentElectBlockHeight(AddressEnum.edge.toString()));
            queryAndUpdateElectNode(AddressEnum.archive, currentElectBlockHeight(AddressEnum.archive.toString()));
        } else {
            queryAndUpdateElectNode(AddressEnum.auditor, height);
            queryAndUpdateElectNode(AddressEnum.edge, height);
            queryAndUpdateElectNode(AddressEnum.archive, height);
        }

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
                if (addressEnum == AddressEnum.auditor) {
                    queryNodeTypes.add(AddressEnum.auditor.name());
                    queryNodeTypes.add(AddressEnum.validator.name());
                } else {
                    queryNodeTypes.add(addressEnum.name());
                }

                /**
                 * 已经当选的节点
                 */
                List<TopNodeElection> electedNodeList = nodeElectionService.getElectedNodes(queryNodeTypes);

                /**
                 * 上一个区块当选的节点map
                 * key=nodeAddress&nodeType
                 */
                Map<String, TopNodeElection> electedNodeMap = new HashMap<>();
                if (electedNodeList != null) {
                    for (TopNodeElection topNodeInfo : electedNodeList) {
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

                if (ObjectUtils.isEmpty(nodeList)) {
                    break;
                }

                saveElectionNode(result, nodeList);

                //转成nodeInfo
                List<TopNodeElection> newElectNodeList = nodeList.stream()
                        .map(currentElectNode -> {
                            TopNodeElection election = new TopNodeElection();
                            election.setAddress(currentElectNode.getAccount());
                            election.setLastElectedTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault()));
                            election.setLastElectedBlockHeight(currentHeight);
                            election.setType(currentElectNode.getNode_type());
                            return election;
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

                for (TopNodeElection election : newElectNodeList) {
                    nodeElectionService.electeNode(election.getAddress(),
                            election.getType(),
                            election.getLastElectedTime(),
                            election.getLastElectedBlockHeight());
                }

                //lastElectNodeList中剩下的是未当选的节点
                nodeElectionService.loseElectNode(electedNodeMap.values());

                setElectBlockHeight(addressEnum.toString(), nextHeight);
                nextHeight++;
            } catch (Exception e) {
                log.error("queryNewElectNode error, nodeType=" + addressEnum.toString() + ", height=" + nextHeight, e);
            }
        }
    }

    /**
     * 当前扫到的选举块高度
     *
     * @return
     */
    private Long currentElectBlockHeight(String nodeType) {
        Object value = redisTemplate.opsForValue().get(TopConstants.ELECTION_BLOCK_HEIGHT_KEY + "_" + nodeType);
        if (value == null) {
            return 0L;
        }
        return Long.parseLong(value.toString());
    }


    /**
     * 设置下一个选举块高度
     * @param nodeType
     * @param height
     */
    private void setElectBlockHeight(String nodeType, long height) {
        redisTemplate.opsForValue().set(TopConstants.ELECTION_BLOCK_HEIGHT_KEY + "_" + nodeType, height);
    }

    /**
     * 构建NodeInfo对象
     *
     * @param nodeInfoResult
     * @return
     */
    private TopNodeInfo createNodeInfo(NodeInfoResult nodeInfoResult) {
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
        topNodeInfo.setVoteAmount(nodeInfoResult.getVote_amount());
        topNodeInfo.setDividenRatio(nodeInfoResult.getDividend_ratio());
        return topNodeInfo;
    }


    /**
     * 构建NodeReward对象
     *
     * @param address
     * @param value
     * @return
     */
    private TopNodeInfo createNodeRewardInfo(String address, NodeRewardValue value) {
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
        if (lastClaimTime != null && lastClaimTime.compareTo(BigInteger.ZERO) > 0) {
            topNodeRewardInfo.setLastClaimTimestamp(TopUtils.convertTimerHeight2Timestamp(lastClaimTime.longValue()));
        }
        if (lastIssueTime != null && lastIssueTime.compareTo(BigInteger.ZERO) > 0) {
            topNodeRewardInfo.setLastIssueTimestamp(TopUtils.convertTimerHeight2Timestamp(lastIssueTime.longValue()));
        }
        return topNodeRewardInfo;
    }

    /**
     * 储存每次的选举节点
     * @param result
     * @param electNodes
     */
    private void saveElectionNode(ElectionBlockResult result,List<Elect_nodes> electNodes){
        Value value = result.getValue();

        List<TopElectionBlock> electionBlock = electNodes.stream()
                .map(node -> buildElectionBlockNode(value, node))
                .collect(Collectors.toList());

        electionBlockService.saveBatch(electionBlock);
    }

    private TopElectionBlock buildElectionBlockNode(Value value, Elect_nodes electNode){
        TopElectionBlock topElectionBlock = new TopElectionBlock();

        topElectionBlock.setBlockHash(value.getHash());
        topElectionBlock.setBlockPrevHash(value.getPrev_hash());
        topElectionBlock.setBlockTimestamp(value.getTimestamp());
        topElectionBlock.setOwner(value.getOwner());
        topElectionBlock.setTimestamp(electNode.getTimestamp().longValue());
        topElectionBlock.setBlockTimerHeight(value.getHeader().getTimerblock_height().longValue());
        topElectionBlock.setAuditorXip(value.getHeader().getAuditor_xip());
        topElectionBlock.setValidator(value.getHeader().getValidator());
        topElectionBlock.setValidatorXip(value.getHeader().getValidator_xip());
        topElectionBlock.setZoneId(value.getBody().getElect_transaction().getZone_id().intValue());
        topElectionBlock.setRoundNo(value.getBody().getElect_transaction().getRound_no().intValue());

        topElectionBlock.setPublicKey(electNode.getPublic_key());
        topElectionBlock.setNodeType(electNode.getNode_type());
        topElectionBlock.setSlotId(electNode.getSlot_id().intValue());
        topElectionBlock.setGroupId(electNode.getGroup_id().intValue());
        topElectionBlock.setStake(electNode.getStake());
        topElectionBlock.setStartTimerHeight(electNode.getStart_timer_height().longValue());
        topElectionBlock.setVersion(electNode.getVersion().intValue());
        topElectionBlock.setAccount(electNode.getAccount());

        return topElectionBlock;
    }
}
