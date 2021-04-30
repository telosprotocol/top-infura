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
 * 
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("top_node_election")
public class TopNodeElection extends Model<TopNodeElection> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * edge节点数量
     */
    @TableField("edge")
    private Integer edge;

    /**
     * archive节点数量
     */
    @TableField("archive")
    private Integer archive;

    /**
     * auditor节点数量
     */
    @TableField("auditor")
    private Integer auditor;

    /**
     * validator节点数量
     */
    @TableField("validator")
    private Integer validator;

    /**
     * root_beacon节点数量
     */
    @TableField("root_beacon")
    private Integer rootBeacon;

    /**
     * sub_beacon节点数量
     */
    @TableField("sub_beacon")
    private Integer subBeacon;

    /**
     * edge节点本轮块高
     */
    @TableField("edgeHeight")
    private String edgeHeight;

    /**
     * archive节点本轮块高
     */
    @TableField("archiveHeight")
    private String archiveHeight;

    /**
     * auditor节点本轮块高
     */
    @TableField("auditorHeight")
    private String auditorHeight;

    /**
     * validator节点本轮块高
     */
    @TableField("validatorHeight")
    private String validatorHeight;

    /**
     * root_beacon节点本轮块高
     */
    @TableField("root_beaconHeight")
    private String rootBeaconheight;

    /**
     * sub_beacon节点本轮块高
     */
    @TableField("sub_beaconHeight")
    private String subBeaconheight;

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
