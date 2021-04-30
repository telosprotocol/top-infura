package org.topnetwork.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("top_node_issuance")
public class TopNodeIssuance extends Model<TopNodeIssuance> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 区块高度
     */
    @TableField("height")
    private Long height;

    @TableField("timestamp")
    private Long timestamp;

    @TableField("archive_reward_ratio")
    private Integer archiveRewardRatio;

    /**
     * 审计奖励比例
     */
    @TableField("auditor_reward_ratio")
    private Integer auditorRewardRatio;

    /**
     * 边缘节点奖励比例
     */
    @TableField("edge_reward_ratio")
    private Integer edgeRewardRatio;

    @TableField("governance_reward_ratio")
    private Integer governanceRewardRatio;

    /**
     * 验证奖励比例
     */
    @TableField("validator_reward_ratio")
    private Integer validatorRewardRatio;

    /**
     * 投票奖励比例
     */
    @TableField("vote_reward_ratio")
    private Integer voteRewardRatio;

    /**
     * 审计工作量
     */
    @TableField("auditor_workload")
    private BigInteger auditorWorkload;

    /**
     * 验证工作量
     */
    @TableField("validator_workload")
    private BigInteger validatorWorkload;

    /**
     * 边缘节点奖励
     */
    @TableField("edge_reward")
    private BigInteger edgeReward;

    @TableField("archive_reward")
    private BigInteger archiveReward;

    /**
     * 验证节点奖励
     */
    @TableField("validator_reward")
    private BigInteger validatorReward;

    /**
     * 审计节点奖励
     */
    @TableField("auditor_reward")
    private BigInteger auditorReward;

    /**
     * 投票奖励
     */
    @TableField("voter_reward")
    private BigInteger voterReward;

    /**
     * 总奖励
     */
    @TableField("total_reward")
    private BigInteger totalReward;

    /**
     * 时钟高度
     */
    @TableField("timer_height")
    private Integer timerHeight;

    @TableField("zec_reward_contract_height")
    private Integer zecRewardContractHeight;

    @TableField("zec_vote_contract_height")
    private Integer zecVoteContractHeight;

    @TableField("zec_workload_contract_height")
    private Integer zecWorkloadContractHeight;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
