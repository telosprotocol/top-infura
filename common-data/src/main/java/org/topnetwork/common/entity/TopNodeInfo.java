package org.topnetwork.common.entity;

import java.math.BigDecimal;
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
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("top_node_info")
public class TopNodeInfo extends Model<TopNodeInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    /**
     * 节点类型
     */
    @TableField("type")
    private String type;

    /**
     * 节点地址
     */
    @TableField("address")
    private String address;

    /**
     * 是否当选
     */
    @TableField("elected")
    private Integer elected;

    /**
     * 上次获得奖励的时间
     */
    @TableField("last_claim_timestamp")
    private Long lastClaimTimestamp;

    /**
     * 上次发放奖励时间
     */
    @TableField("last_issue_timestamp")
    private Long lastIssueTimestamp;

    /**
     * 上次当选时间
     */
    @TableField("last_elected_time")
    private LocalDateTime lastElectedTime;

    /**
     * 上次当选的区块高度
     */
    @TableField("last_elected_block_height")
    private Long lastElectedBlockHeight;

    /**
     * 未领取奖励
     */
    @TableField("unclaim_amount")
    private BigInteger unclaimAmount;

    /**
     * 总奖励
     */
    @TableField("accumulated_amount")
    private BigInteger accumulatedAmount;

    @TableField("auditor_credit")
    private BigDecimal auditorCredit;

    @TableField("auditor_stake")
    private BigInteger auditorStake;

    @TableField("validator_credit")
    private BigDecimal validatorCredit;

    @TableField("validator_stake")
    private BigInteger validatorStake;

    @TableField("dividen_ratio")
    private BigInteger dividenRatio;

    @TableField("network_id")
    private String networkId;

    @TableField("node_deposit")
    private BigInteger nodeDeposit;

    @TableField("node_sign_key")
    private String nodeSignKey;

    @TableField("vote_amount")
    private BigInteger voteAmount;

    @TableField("rec_stake")
    private BigInteger recStake;

    @TableField("zec_stake")
    private BigInteger zecStake;

    @TableField("version")
    private BigInteger version;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
