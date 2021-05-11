package org.topnetwork.api.bean.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @author CasonCai
 * @since 2021/5/7 2:38 下午
 **/
@ApiModel("节点信息")
@Data
public class NodeInfoResp {

    @ApiModelProperty("节点名称")
    private String name;
    
    @ApiModelProperty("节点类型")
    private String type;

    @ApiModelProperty("节点地址")
    private String address;

    @ApiModelProperty("上次获得奖励的时间")
    private Long lastClaimTimestamp;
    /**
     * 上次发放奖励时间
     */
    @ApiModelProperty("上次发放奖励时间")
    private Long lastIssueTimestamp;
    /**
     * 未领取奖励
     */
    @ApiModelProperty("未领取奖励")
    private BigInteger unclaimAmount;
    /**
     * 总奖励
     */
    @ApiModelProperty("总奖励")
    private BigInteger accumulatedAmount;

    @ApiModelProperty("审计节点credit")
    private BigDecimal auditorCredit;

    @ApiModelProperty("审计节点stake")
    private BigInteger auditorStake;

    @ApiModelProperty("验证节点credit")
    private BigDecimal validatorCredit;

    @ApiModelProperty("验证节点stake")
    private BigInteger validatorStake;

    @ApiModelProperty("分红比例")
    private BigInteger dividenRatio;

    @ApiModelProperty("network_id")
    private String networkId;

    @ApiModelProperty("保证金")
    private BigInteger nodeDeposit;

    @ApiModelProperty("node_sign_key")
    private String nodeSignKey;

    @ApiModelProperty("投票数")
    private BigInteger voteAmount;

    @ApiModelProperty("rec_stake")
    private BigInteger recStake;

    @ApiModelProperty("zec_stake")
    private BigInteger zecStake;

    @ApiModelProperty("审计节点是否当选")
    private Boolean auditorElected;

    private LocalDateTime auditorElectedTime;

    @ApiModelProperty("验证节点是否当选")
    private Boolean validatorElected;
    @ApiModelProperty("验证节点当选时间")
    private LocalDateTime validatorElectedTime;

    @ApiModelProperty("边缘节点是否当选")
    private Boolean edgeElected;
    @ApiModelProperty("边缘节点当选时间")
    private LocalDateTime edgeElectedTime;

    @ApiModelProperty("储存节点是否当选")
    private Boolean archiveElected;

    private LocalDateTime archiveElectedTime;

    @ApiModelProperty("version")
    private BigInteger version;

}
