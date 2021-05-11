package org.topnetwork.api.bean.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.topnetwork.common.enums.UnitBlockType;

/**
 * @author CasonCai
 * @since 2021/5/7 9:43 上午
 **/
@ApiModel("unitblock")
@Data
public class UnitBlockResp {
    @ApiModelProperty("区块高度")
    private Long height;

    /**
     * 区块hash
     */
    @ApiModelProperty("区块hash")
    private String hash;

    /**
     * TableBlock hash
     */
    @ApiModelProperty("TableBlock hash")
    private String tableblockHash;

    /**
     * 上一个区块hash
     */
    @ApiModelProperty("上一个区块hash")
    private String preHash;

    @ApiModelProperty("zoneId")
    private Integer zoneId;

    @ApiModelProperty("tableId")
    private Integer tableId;

    @ApiModelProperty("shardId")
    private Integer shardId;

    @ApiModelProperty("timestamp")
    private Long timestamp;

    @ApiModelProperty("clusterId")
    private Integer clusterId;

    /**
     * 矿工地址
     */
    @ApiModelProperty("矿工地址")
    private String owner;

    /**
     * 审计节点地址
     */
    @ApiModelProperty("审计节点地址")
    private String auditor;

    /**
     * 验证节点地址
     */
    @ApiModelProperty("验证节点地址")
    private String validator;

    /**
     * 打包交易数量
     */
    @ApiModelProperty("打包交易数量")
    private Integer txCount;

    /**
     * 区块类型
     */
    @ApiModelProperty("区块类型")
    private UnitBlockType blockType;

}
