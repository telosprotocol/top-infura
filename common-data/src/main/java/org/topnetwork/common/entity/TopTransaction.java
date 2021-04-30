package org.topnetwork.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.topnetwork.common.enums.ChainZoneType;
import org.topnetwork.common.enums.TxType;

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
@TableName("top_transaction")
public class TopTransaction extends Model<TopTransaction> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * hash
     */
    @TableField("hash")
    private String hash;

    /**
     * 交易类型
     */
    @TableField("type")
    private TxType type;

    /**
     * 交易时间
     */
    @TableField("timestamp")
    private Long timestamp;

    /**
     * 发送人
     */
    @TableField("`from`")
    private String from;

    /**
     * 接收人
     */
    @TableField("`to`")
    private String to;

    /**
     * 数量
     */
    @TableField("amount")
    private BigInteger amount;

    @TableField("tx_fee")
    private BigInteger txFee;

    /**
     * 备注
     */
    @TableField("note")
    private String note;

    /**
     * 上笔交易序号
     */
    @TableField("last_tx_nonce")
    private BigInteger lastTxNonce;

    /**
     * 交易体大小
     */
    @TableField("tx_len")
    private BigInteger txLen;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 分片from
     */
    @TableField("shard_from")
    private Integer shardFrom;

    /**
     * 分片to
     */
    @TableField("shard_to")
    private Integer shardTo;

    /**
     * used_deposit
     */
    @TableField("used_deposit")
    private BigInteger usedDeposit;

    /**
     * gas used
     */
    @TableField("gas_used")
    private Long gasUsed;

    /**
     * disk used
     */
    @TableField("disk_used")
    private Long diskUsed;

    /**
     * 交易保证金，单位uTOP
     */
    @TableField("tx_deposit")
    private BigInteger txDeposit;

    /**
     * zoneType
     */
    @TableField("chain_zone_type")
    private ChainZoneType chainZoneType;

    /**
     * 执行合约的函数名
     */
    @TableField("func_name")
    private String funcName;

    /**
     * 执行合约的参数
     */
    @TableField("func_input")
    private String funcInput;

    /**
     * 币名
     */
    @TableField("token_name")
    private String tokenName;

    /**
     * 一次合约调用中，合约账户愿意支付的最大tgas
     */
    @TableField("tgas_limit")
    private String tgasLimit;

    /**
     * 合约代码
     */
    @TableField("code")
    private String code;

    /**
     * 兑票数
     */
    @TableField("vote_num")
    private String voteNum;

    /**
     * 票的锁定时长（天）
     */
    @TableField("lock_duration")
    private String lockDuration;

    /**
     * 属性的key
     */
    @TableField("account_key")
    private String accountKey;

    /**
     * 设置的值
     */
    @TableField("key_value")
    private String keyValue;

    /**
     * 版本
     */
    @TableField("version")
    private String version;

    /**
     * 解锁类型
     */
    @TableField("unlock_type")
    private String unlockType;

    /**
     * 解锁类型对应的值
     */
    @TableField("unlock_values")
    private String unlockValues;

    /**
     * 序列化的锁定上下文
     */
    @TableField("params")
    private String params;

    /**
     * 锁定交易的hash
     */
    @TableField("lock_tran_hash")
    private String lockTranHash;

    /**
     * 签名
     */
    @TableField("signatures")
    private String signatures;

    /**
     * 别名
     */
    @TableField("name")
    private String name;

    /**
     * recv_used_deposit
     */
    @TableField("recv_used_deposit")
    private BigInteger recvUsedDeposit;

    /**
     * recv_used_gas
     */
    @TableField("recv_used_gas")
    private BigInteger recvUsedGas;

    /**
     * recv_used_disk
     */
    @TableField("recv_used_disk")
    private BigInteger recvUsedDisk;

    /**
     * send_used_deposit
     */
    @TableField("send_used_deposit")
    private BigInteger sendUsedDeposit;

    /**
     * send_used_gas
     */
    @TableField("send_used_gas")
    private BigInteger sendUsedGas;

    /**
     * send_used_disk
     */
    @TableField("send_used_disk")
    private BigInteger sendUsedDisk;

    /**
     * sender_action_param
     */
    @TableField("sender_action_param")
    private String senderActionParam;

    /**
     * receiver_action_param
     */
    @TableField("receiver_action_param")
    private String receiverActionParam;

    @TableField("send_unitblock_hash")
    private String sendUnitBlockHash;

    @TableField("rec_unitblock_hash")
    private String recUnitBlockHash;

    @TableField("confirm_unitblock_hash")
    private String confirmUnitBlockHash;

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

    /**
     * unit block hash
     */
    @TableField("unit_block_hash")
    private String unitBlockHash;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
