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
@TableName("top_node_info")
public class NodeInfo extends Model<NodeInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 节点地址
     */
    @TableField("address")
    private String address;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 节点类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 上次获得奖励的时间
     */
    @TableField("last_claim_time")
    private LocalDateTime lastClaimTime;

    /**
     * 上次当选时间
     */
    @TableField("last_elected_time")
    private LocalDateTime lastElectedTime;

    /**
     * 上次发放奖励时间
     */
    @TableField("last_issue_time")
    private LocalDateTime lastIssueTime;

    /**
     * 未领取奖励
     */
    @TableField("unclaim_amount")
    private BigDecimal unclaimAmount;

    /**
     * 总奖励
     */
    @TableField("accumulated_amount")
    private BigDecimal accumulatedAmount;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
