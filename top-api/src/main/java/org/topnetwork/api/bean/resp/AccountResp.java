package org.topnetwork.api.bean.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.topnetwork.common.enums.ChainZoneType;

import java.math.BigInteger;

/**
 * @author CasonCai
 * @since 2021/5/7 9:43 上午
 **/
@ApiModel("账户")
@Data
public class AccountResp {

    /**
     * 账户地址
     */
    @ApiModelProperty("账户地址")
    private String address;

    /**
     * 账户余额
     */
    @ApiModelProperty("账户余额")
    private BigInteger balance;

    /**
     * 锁定金额
     */
    @ApiModelProperty("锁定金额")
    private BigInteger lockBalance;

    /**
     * 交易数
     */
    @ApiModelProperty("交易数")
    private BigInteger txNum;

    /**
     * 所属分片
     */
    @ApiModelProperty("所属分片")
    private Integer shard;

    /**
     * 总gas
     */
    @ApiModelProperty("总gas")
    private BigInteger gasTotal;

    /**
     * 未使用gas ，available_gas
     */
    @ApiModelProperty("未使用gas")
    private BigInteger gasUnUse;

    /**
     * DISK总质押
     */
    @ApiModelProperty("DISK总质押")
    private BigInteger diskStakedToken;

    /**
     * GAS总质押
     */
    @ApiModelProperty("GAS总质押")
    private BigInteger gasStakedToken;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long timestamp;

    /**
     * zoneType
     */
    @ApiModelProperty("zoneType")
    private ChainZoneType chainZoneType;

    /**
     * clusterId
     */
    @ApiModelProperty("clusterId")
    private Integer clusterId;

    /**
     * zoneId
     */
    @ApiModelProperty("zoneId")
    private Integer zoneId;

    /**
     * 除发送方交易消耗的gas，单位Tgas
     */
    @ApiModelProperty("除发送方交易消耗的gas")
    private BigInteger lockGas;

    /**
     * 该账户所有已经销毁的TOP token，单位uTOP
     */
    @ApiModelProperty("该账户所有已经销毁的TOP")
    private BigInteger burnedToken;

    /**
     * 解锁中的兑换gas的TOP token
     */
    @ApiModelProperty("解锁中的兑换gas的TOP")
    private BigInteger unlockGasStaked;

    /**
     * 扣除发送方交易保证金，单位uTOP
     */
    @ApiModelProperty("扣除发送方交易保证金")
    private BigInteger lockDepositBalance;

    /**
     * 解锁中的兑换disk的TOP token
     */
    @ApiModelProperty("解锁中的兑换disk的TOP")
    private BigInteger unlockDiskStaked;

    /**
     * 新共识成功的交易的unit block高度
     */
    @ApiModelProperty("新共识成功的交易的unit")
    private String latestUnitHeight;

    /**
     * 最新共识成功的交易xx64hash
     */
    @ApiModelProperty("最新共识成功的交易xx64hash")
    private String latestTxHashXxhash64;

    /**
     * 兑换选票锁定的TOP token，单位uTOP
     */
    @ApiModelProperty("兑换选票锁定的TOP")
    private BigInteger voteStakedToken;

    /**
     * 该账户最新共识成功的交易序号，唯一
     */
    @ApiModelProperty("该账户最新共识成功的交易序号")
    private BigInteger nonce;

    /**
     * 该账户未使用选票数量
     */
    @ApiModelProperty("该账户未使用选票数量")
    private BigInteger unusedVoteAmount;

    /**
     * 最新共识成功的交易hash
     */
    @ApiModelProperty("最新共识成功的交易hash")
    private String latestTxHash;
    


}
