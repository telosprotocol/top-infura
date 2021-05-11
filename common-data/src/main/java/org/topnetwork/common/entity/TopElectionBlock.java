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
 * 选举块信息
 * </p>
 *
 * @author CasonCai
 * @since 2021-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("top_election_block")
public class TopElectionBlock extends Model<TopElectionBlock> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 选举块hash
     */
    @TableField("block_hash")
    private String blockHash;

    /**
     * 选举块时间高度
     */
    @TableField("block_timer_height")
    private Long blockTimerHeight;

    /**
     * 上一个选举块hash
     */
    @TableField("block_prev_hash")
    private String blockPrevHash;

    @TableField("auditor_xip")
    private String auditorXip;

    /**
     * 验证节点
     */
    @TableField("validator")
    private String validator;

    @TableField("validator_xip")
    private String validatorXip;

    /**
     * 选举块owner
     */
    @TableField("owner")
    private String owner;

    /**
     * 选举块时间戳
     */
    @TableField("block_timestamp")
    private Long blockTimestamp;

    /**
     * 选举的节点账号
     */
    @TableField("account")
    private String account;

    @TableField("group_id")
    private Integer groupId;

    @TableField("node_type")
    private String nodeType;

    @TableField("public_key")
    private String publicKey;

    @TableField("slot_id")
    private Integer slotId;

    @TableField("stake")
    private BigInteger stake;

    /**
     * 节点选举时间
     */
    @TableField("start_timer_height")
    private Long startTimerHeight;

    /**
     * 节点选举时间戳
     */
    @TableField("timestamp")
    private Long timestamp;

    /**
     * 节点版本
     */
    @TableField("version")
    private Integer version;

    @TableField("zone_id")
    private Integer zoneId;

    @TableField("round_no")
    private Integer roundNo;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
