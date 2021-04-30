package org.topnetwork.analysis.block;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.topnetwork.common.entity.TopNodeIssuance;
import org.topnetwork.common.service.TopNodeIssuanceService;
import org.topnetwork.common.utils.TopUtils;
import org.topnetwork.grpclib.pojo.node.IssuanceDetailResult;
import org.topnetwork.grpclib.pojo.node.IssuanceDetailValue;
import org.topnetwork.grpclib.pojo.unit.Lightunit;
import org.topnetwork.grpclib.pojo.unit.UnitBlockResult;
import org.topnetwork.grpclib.pojo.unit.UnitBlockValue;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用来同步节点每期的工作量、奖励统计数据
 * @author CasonCai
 * @since 2021/4/27 下午4:17
 **/
@Component
public class NodeIssuanceLoader {

    /**
     * 用来查询奖励节点的地址
     */
    private String address = "T200024uV5yB1ZCnXe7SbViA86ufhouFjpDKNRd3X@0";

    @Autowired
    private TopNodeIssuanceService topNodeIssuanceService;

    @Autowired
    TopGrpcClient topGrpcClient;

    public void loadNextIssuance() {
        Long currentIssuanceHeight = topNodeIssuanceService.getCurrentHeight();

        Long height = currentIssuanceHeight + 1;
        while (true) {
            boolean success = loadIssuance(height);
            if (!success) {
                break;
            }
            height++;
        }
    }

    public boolean loadIssuance(Long height) {
        UnitBlockResult unitBlockResult = topGrpcClient.getUnitBlock(address, height);
        if (unitBlockResult.getValue() == null) {
            return false;
        }

        if (haveStatReward(unitBlockResult.getValue())) {
            doLoadIssuance(height);
        }

        return true;
    }

    private void doLoadIssuance(Long height) {
        IssuanceDetailResult result = topGrpcClient.getIssuanceDetail(height);
        if(result.getData() == null){
            return;
        }

        List<TopNodeIssuance> topNodeIssuanceList = parseResult(result);
        topNodeIssuanceService.saveBatch(topNodeIssuanceList);
    }

    //TODO
    private List<TopNodeIssuance> parseResult(IssuanceDetailResult result) {
        return parseNodeRewardStatResult(result);
    }

    /**
     * 是否有统计块
     *
     * @param unitBlock
     * @return
     */
    private boolean haveStatReward(UnitBlockValue unitBlock) {
        Lightunit lightunit = unitBlock.getBody().getLightunit();
        if (lightunit == null) {
            return false;
        }

        Object cumstomPropertyKey = lightunit.getLightunit_state().getCustom_property_key();
        if (cumstomPropertyKey instanceof List) {
            return ((List<?>) cumstomPropertyKey).contains("#146");
        }

        return false;
    }


    /**
     * 解析奖励统计数据
     *
     * @param issuanceDetailResult
     * @return
     */
    public List<TopNodeIssuance> parseNodeRewardStatResult(IssuanceDetailResult issuanceDetailResult) {
        IssuanceDetailValue issuanceDetailValue = null;
        Long height = null;
        for (Map.Entry<String, IssuanceDetailValue> entry : issuanceDetailResult.getData().entrySet()) {
            height = Long.valueOf(entry.getKey());
            issuanceDetailValue = entry.getValue();
        }

        if (issuanceDetailValue == null) {
            return null;
        }

        //统计
        Map<String, BigInteger> auditorWorkloads = parseWorkloads(issuanceDetailValue.getAuditor_workloads());
        Map<String, BigInteger> validatorWorkload = parseWorkloads(issuanceDetailValue.getValidator_workloads());
        Map<String, RewardData> rewardDatas = parseRewards(issuanceDetailValue.getNode_rewards());

        Map<String, TopNodeIssuance> nodeIssuanceMap = new HashMap<>();

        addAuditorWorkloadToStat(nodeIssuanceMap, auditorWorkloads);
        addValidatorWorkloadToStat(nodeIssuanceMap, validatorWorkload);
        addRewardToStat(nodeIssuanceMap, rewardDatas);

        List<TopNodeIssuance> topNodeIssuanceList = nodeIssuanceMap.values().stream().collect(Collectors.toList());

        for (TopNodeIssuance topNodeIssuance : topNodeIssuanceList) {
            topNodeIssuance.setArchiveRewardRatio(issuanceDetailValue.getArchive_reward_ratio());
            topNodeIssuance.setAuditorRewardRatio(issuanceDetailValue.getAuditor_reward_ratio());
            topNodeIssuance.setEdgeRewardRatio(issuanceDetailValue.getEdge_reward_ratio());
            topNodeIssuance.setGovernanceRewardRatio(issuanceDetailValue.getGovernance_reward_ratio());
            topNodeIssuance.setValidatorRewardRatio(issuanceDetailValue.getValidator_reward_ratio());
            topNodeIssuance.setVoteRewardRatio(issuanceDetailValue.getVote_reward_ratio());
            topNodeIssuance.setTimerHeight(issuanceDetailValue.getOnchain_timer_round());
            topNodeIssuance.setZecRewardContractHeight(issuanceDetailValue.getZec_reward_contract_height());
            topNodeIssuance.setZecVoteContractHeight(issuanceDetailValue.getZec_vote_contract_height());
            topNodeIssuance.setZecWorkloadContractHeight(issuanceDetailValue.getZec_workload_contract_height());
            topNodeIssuance.setHeight(height);
        }

        return nodeIssuanceMap.values().stream().collect(Collectors.toList());
    }


    /**
     * @param nodeIssuanceMap
     * @param auditorWorkloads
     */
    public void addAuditorWorkloadToStat(Map<String, TopNodeIssuance> nodeIssuanceMap, Map<String, BigInteger> auditorWorkloads) {
        if (auditorWorkloads == null) {
            return;
        }

        for (Map.Entry<String, BigInteger> entry : auditorWorkloads.entrySet()) {
            String nodeAddr = entry.getKey();
            TopNodeIssuance topNodeIssuance = nodeIssuanceMap.getOrDefault(nodeAddr, new TopNodeIssuance());
            topNodeIssuance.setAddress(nodeAddr);
            topNodeIssuance.setAuditorWorkload(entry.getValue());
            nodeIssuanceMap.put(nodeAddr, topNodeIssuance);
        }
    }

    public void addValidatorWorkloadToStat(Map<String, TopNodeIssuance> nodeIssuanceMap, Map<String, BigInteger> validatorWorkload) {
        if (validatorWorkload == null) {
            return;
        }

        for (Map.Entry<String, BigInteger> entry : validatorWorkload.entrySet()) {
            String nodeAddr = entry.getKey();
            TopNodeIssuance topNodeIssuance = nodeIssuanceMap.getOrDefault(nodeAddr, new TopNodeIssuance());
            topNodeIssuance.setAddress(nodeAddr);
            topNodeIssuance.setValidatorWorkload(entry.getValue());
            nodeIssuanceMap.put(nodeAddr, topNodeIssuance);
        }
    }

    public void addRewardToStat(Map<String, TopNodeIssuance> nodeIssuanceMap, Map<String, RewardData> rewardDataMap) {
        if (rewardDataMap == null) {
            return;
        }

        for (Map.Entry<String, RewardData> entry : rewardDataMap.entrySet()) {
            String nodeAddr = entry.getKey();
            RewardData rewardData = entry.getValue();
            TopNodeIssuance topNodeIssuance = nodeIssuanceMap.getOrDefault(nodeAddr, new TopNodeIssuance());
            topNodeIssuance.setAddress(nodeAddr);
            topNodeIssuance.setArchiveReward(TopUtils.chainAmount(rewardData.getArchive_reward()));
            topNodeIssuance.setEdgeReward(TopUtils.chainAmount(rewardData.getEdge_reward()));
            topNodeIssuance.setAuditorReward(TopUtils.chainAmount(rewardData.getAuditor_reward()));
            topNodeIssuance.setArchiveReward(TopUtils.chainAmount(rewardData.getArchive_reward()));
            topNodeIssuance.setVoterReward(TopUtils.chainAmount(rewardData.getVoter_reward()));
            topNodeIssuance.setTotalReward(TopUtils.chainAmount(rewardData.getTotal_reward()));
            nodeIssuanceMap.put(nodeAddr, topNodeIssuance);
        }
    }

    /**
     * 解析节点工作量数据
     *
     * @param workloads
     * @return
     */
    public Map<String, BigInteger> parseWorkloads(Map<String, Map<String, BigInteger>> workloads) {
        if (workloads == null) {
            return null;
        }

        Map<String, BigInteger> totalworkloads = new HashMap<>();

        for (Map.Entry<String, Map<String, BigInteger>> entry : workloads.entrySet()) {
            String shardId = entry.getKey();
            Map<String, BigInteger> value = entry.getValue();

            for (Map.Entry<String, BigInteger> valueEntry : value.entrySet()) {
                String nodeAddr = valueEntry.getKey();
                BigInteger workload = valueEntry.getValue();
                //TODO
                if (ObjectUtils.nullSafeEquals(nodeAddr, "cluster_total_workload")) {
                    continue;
                }

                BigInteger existWorkload = totalworkloads.get(nodeAddr);
                if (existWorkload == null) {
                    totalworkloads.put(nodeAddr, workload);
                } else {
                    totalworkloads.put(nodeAddr, workload.add(existWorkload));
                }
            }
        }

        return totalworkloads;
    }


    /**
     * 解析节点奖励数据
     *
     * @param rewards
     * @return
     */
    public Map<String, RewardData> parseRewards(Map<String, String> rewards) {

        if (rewards == null) {
            return null;
        }

        Map<String, RewardData> rewardDataMap = new HashMap<>();

        for (Map.Entry<String, String> entry : rewards.entrySet()) {
            String nodeAddr = entry.getKey();
            String value = entry.getValue();
            RewardData rewardData = parseReward(value);

            rewardDataMap.put(nodeAddr, rewardData);
        }

        return rewardDataMap;
    }

    private RewardData parseReward(String rewardContent) {
        rewardContent = rewardContent.replaceAll(" ", "");
        String[] rewardTypes = rewardContent.split(",");

        BigDecimal edge_reward = null;
        BigDecimal archive_reward = null;
        BigDecimal validator_reward = null;
        BigDecimal auditor_reward = null;
        BigDecimal voter_reward = null;

        for (String rewardType : rewardTypes) {
            String[] reward = rewardType.split(":");
            String type = reward[0];
            String amountStr = reward[1];

            if ("edge_reward".equals(type)) {
                edge_reward = new BigDecimal(amountStr);
            }

            if ("archive_reward".equals(type)) {
                archive_reward = new BigDecimal(amountStr);
            }

            if ("validator_reward".equals(type)) {
                validator_reward = new BigDecimal(amountStr);
            }

            if ("auditor_reward".equals(type)) {
                auditor_reward = new BigDecimal(amountStr);
            }

            if ("voter_reward".equals(type)) {
                voter_reward = new BigDecimal(amountStr);
            }
        }
        return new RewardData(edge_reward, archive_reward, validator_reward, auditor_reward, voter_reward);
    }


    @Data
    public static class RewardData {
        private BigDecimal edge_reward;
        private BigDecimal archive_reward;
        private BigDecimal validator_reward;
        private BigDecimal auditor_reward;
        private BigDecimal voter_reward;
        private BigDecimal total_reward;

        public RewardData(BigDecimal edge_reward, BigDecimal archive_reward, BigDecimal validator_reward, BigDecimal auditor_reward, BigDecimal voter_reward) {
            this.edge_reward = edge_reward;
            this.archive_reward = archive_reward;
            this.validator_reward = validator_reward;
            this.auditor_reward = auditor_reward;
            this.voter_reward = voter_reward;

            total_reward = new BigDecimal("0");
            total_reward = total_reward.add(edge_reward);
            total_reward = total_reward.add(archive_reward);
            total_reward = total_reward.add(validator_reward);
            total_reward = total_reward.add(auditor_reward);
            total_reward = total_reward.add(voter_reward);
        }

    }

}
