package org.topnetwork.common.entity;

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
 * unitblock表
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("top_unit_block")
public class UnitBlock extends Model<UnitBlock> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 区块高度
     */
    @TableField("height")
    private Long height;

    /**
     * 区块hash
     */
    @TableField("hash")
    private String hash;

    @TableField("zone_id")
    private Integer zoneId;

    @TableField("table_id")
    private Integer tableId;

    @TableField("shard_id")
    private Integer shardId;

    @TableField("timestamp")
    private Long timestamp;

    @TableField("cluster_id")
    private Integer clusterId;

    /**
     * 矿工地址
     */
    @TableField("miner_address")
    private String minerAddress;

    /**
     * 审计节点地址
     */
    @TableField("auditor_address")
    private String auditorAddress;

    /**
     * 验证节点地址
     */
    @TableField("validator_address")
    private String validatorAddress;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
