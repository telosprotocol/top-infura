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
 * table块表
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("top_table_block")
public class TableBlock extends Model<TableBlock> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * table块高度
     */
    @TableField("height")
    private Long height;

    /**
     * 产生table块的账号地址
     */
    @TableField("miner_address")
    private String minerAddress;

    /**
     * table块hash
     */
    @TableField("hash")
    private String hash;

    /**
     * 上一个table块hash
     */
    @TableField("pre_hash")
    private String preHash;

    /**
     * 单元块tableId
     */
    @TableField("table_id")
    private Integer tableId;

    /**
     * table块出块时间
     */
    @TableField("timestamp")
    private Long timestamp;

    /**
     * 本块的auditor leader节点(xip)
     */
    @TableField("auditor_xip")
    private String auditorXip;

    /**
     * 时钟块高度
     */
    @TableField("timer_height")
    private Long timerHeight;

    /**
     * 本块的validator leader矿工账户地址
     */
    @TableField("validator_address")
    private String validatorAddress;

    /**
     * 本块的validator leader节点(xip)
     */
    @TableField("validator_xip")
    private String validatorXip;

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
