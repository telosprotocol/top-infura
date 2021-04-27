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
import org.topnetwork.common.enums.UnitBlockType;

/**
 * <p>
 * unitblock表
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-21
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

    /**
     * 上一个区块hash
     */
    @TableField("pre_hash")
    private String preHash;

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
    @TableField("owner")
    private String owner;

    /**
     * 审计节点地址
     */
    @TableField("auditor")
    private String auditor;

    /**
     * 验证节点地址
     */
    @TableField("validator")
    private String validator;

    /**
     * 打包交易数量
     */
    @TableField("tx_count")
    private Integer txCount;

    /**
     * 区块类型
     */
    @TableField("block_type")
    private UnitBlockType blockType;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
