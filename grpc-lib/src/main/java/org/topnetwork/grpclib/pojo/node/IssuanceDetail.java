package org.topnetwork.grpclib.pojo.node;

import lombok.Data;

import java.math.BigInteger;

/**
 * 一期的工作量、奖励详情
 * @author CasonCai
 * @since 2021/4/27 下午2:51
 **/
@Data
public class IssuanceDetail {

    private String address;

    private Integer archive_reward_ratio;

    private Integer auditor_reward_ratio;

    private Integer validator_reward_ratio;
    private Integer edge_reward_ratio;
    private Integer vote_reward_ratio;
    private Integer governance_reward_ratio;

    /**
     * 时间高度
     */
    private BigInteger onchain_timer_round;

    private Integer auditor_workload;

    private Integer validator_workload;

    private BigInteger edge_reward;
    private BigInteger archive_reward;
    private BigInteger validator_reward;
    private BigInteger auditor_reward;
    private BigInteger voter_reward;
    private BigInteger total_reward;

    private Integer zec_reward_contract_height;

    private Integer zec_vote_contract_height;

    private Integer zec_workload_contract_height;

}
