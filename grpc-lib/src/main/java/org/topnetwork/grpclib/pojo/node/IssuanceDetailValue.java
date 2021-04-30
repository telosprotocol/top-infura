package org.topnetwork.grpclib.pojo.node;

import lombok.Data;

import java.math.BigInteger;
import java.util.Map;

/**
 * @author CasonCai
 * @since 2021/4/27 上午11:06
 **/
@Data
public class IssuanceDetailValue {

    private Integer archive_reward_ratio;
    private Integer auditor_group_count;
    private Integer auditor_reward_ratio;
    private Integer edge_reward_ratio;
    private Integer governance_reward_ratio;
    private Integer onchain_timer_round;
    private Integer validator_group_count;
    private Integer validator_reward_ratio;
    private Integer vote_reward_ratio;
    private Integer zec_reward_contract_height;
    private Integer zec_vote_contract_height;
    private Integer zec_workload_contract_height;

    private Map<String, String> node_rewards;
    private Map<String, Map<String, BigInteger>> validator_workloads;
    private Map<String, Map<String, BigInteger>> auditor_workloads;

}
