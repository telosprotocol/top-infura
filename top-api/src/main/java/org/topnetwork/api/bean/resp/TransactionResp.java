package org.topnetwork.api.bean.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.topnetwork.common.enums.ChainZoneType;
import org.topnetwork.common.enums.TxType;

import java.math.BigInteger;

/**
 * @author CasonCai
 * @since 2021/5/7 9:43 上午
 **/
@ApiModel("交易")
@Data
public class TransactionResp {
    @ApiModelProperty(value = "发起方")
    private String from;

    @ApiModelProperty(value = "接收方")
    private String to;

    @ApiModelProperty(value = "交易hash")
    private String hash;

    @ApiModelProperty(value = "交易类型")
    private TxType txType;
    @ApiModelProperty(value = "交易金额")
    private BigInteger amount;
    @ApiModelProperty(value = "币种")
    private String tokenName;
    @ApiModelProperty(value = "备注")
    private String note;
    @ApiModelProperty(value = "发起方shard")
    private Integer shardFrom;
    @ApiModelProperty(value = "接收方shard")
    private Integer shardTo;
    @ApiModelProperty(value = "gas费")
    private BigInteger gasUsed;
    @ApiModelProperty(value = "gaslimit")
    private BigInteger tgasLimit;

    @ApiModelProperty(value = "交易类型")
    private ChainZoneType chainZoneType;
    @ApiModelProperty(value = "合约函数名称")
    private String funcName;
    @ApiModelProperty(value = "合约函数输入数据")
    private String funcInput;
    @ApiModelProperty(value = "合约代码")
    private String code;
    @ApiModelProperty(value = "兑票数")
    private String voteNum;
    @ApiModelProperty(value = "票的锁定时长（天）")
    private String lockDuration;
    @ApiModelProperty(value = "交易时间戳")
    private Long timestampe;

    @ApiModelProperty(value = "交易状态")
    private String status;
    @ApiModelProperty(value = "交易发送unitblock hash")
    private String sendUnitBlockHash;
    @ApiModelProperty(value = "交易接收unitblock hash")
    private String recUnitBlockHash;
    @ApiModelProperty(value = "交易确认unitblock hash")
    private String confirmUnitBlockHash;


}
