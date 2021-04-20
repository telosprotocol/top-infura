package org.topnetwork.common.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("top_node_workload")
public class NodeWorkload extends Model<NodeWorkload> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("address")
    private String address;

    @TableField("timestamp")
    private String timestamp;

    /**
     * 区块高度
     */
    @TableField("block_height")
    private Integer blockHeight;

    /**
     * 区块hash
     */
    @TableField("block_hash")
    private String blockHash;

    /**
     * 边缘节点奖励
     */
    @TableField("edge_reward")
    private BigDecimal edgeReward;

    /**
     * 存档奖励
     */
    @TableField("archive_reward")
    private BigDecimal archiveReward;

    /**
     * 验证奖励
     */
    @TableField("validator_reward")
    private BigDecimal validatorReward;

    /**
     * 审查奖励
     */
    @TableField("auditor_reward")
    private BigDecimal auditorReward;

    @TableField("voter_reward")
    private BigDecimal voterReward;

    /**
     * 验证工作量
     */
    @TableField("validator_workload")
    private String validatorWorkload;

    /**
     * 审查工作量
     */
    @TableField("auditor_workload")
    private String auditorWorkload;

    /**
     * 总奖励
     */
    @TableField("total_reward")
    private BigDecimal totalReward;

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
