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
import org.topnetwork.common.enums.BlockType;

/**
 * <p>
 * 
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("top_block_scan")
public class TopBlockScan extends Model<TopBlockScan> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("address")
    private String address;

    @TableField("scan_height")
    private Long scanHeight;

    @TableField("block_type")
    private BlockType blockType;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
