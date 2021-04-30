package org.topnetwork.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("top_shard")
public class TopShard extends Model<TopShard> {

    private static final long serialVersionUID = 1L;

    /**
     * 分片Id
     */
    @TableField("shardId")
    private String shardId;

    /**
     * clusterId
     */
    @TableField("clusterId")
    private String clusterId;

    /**
     * zoneId
     */
    @TableField("zoneId")
    private String zoneId;

    /**
     * 分片下单元块数
     */
    @TableField("blockSize")
    private Integer blockSize;

    /**
     * 分片下账户数
     */
    @TableField("accountSize")
    private Integer accountSize;

    /**
     * 分片下交易数量
     */
    @TableField("transactionSize")
    private Integer transactionSize;

    /**
     * 当前tps
     */
    @TableField("tps")
    private Long tps;

    /**
     * 创建时间
     */
    @TableField("created_date")
    private LocalDateTime createdDate;

    /**
     * 最后修改时间
     */
    @TableField("modified_date")
    private LocalDateTime modifiedDate;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
