package org.topnetwork.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("top_account")
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 账户类型
     */
    @TableField("type")
    private String type;

    /**
     * 账户地址
     */
    @TableField("address")
    private String address;

    /**
     * 账户余额
     */
    @TableField("balance")
    private String balance;

    /**
     * 锁定金额
     */
    @TableField("lock_balance")
    private String lockBalance;

    /**
     * 交易数
     */
    @TableField("tx_num")
    private String txNum;

    /**
     * 所属分片
     */
    @TableField("shard")
    private Integer shard;

    /**
     * 总gas
     */
    @TableField("gas_total")
    private String gasTotal;

    /**
     * 未使用gas ，available_gas
     */
    @TableField("gas_un_use")
    private String gasUnUse;

    /**
     * DISK总质押
     */
    @TableField("disk_staked_token")
    private String diskStakedToken;

    /**
     * GAS总质押
     */
    @TableField("gas_staked_token")
    private String gasStakedToken;

    /**
     * 创建时间
     */
    @TableField("timestamp")
    private Long timestamp;

    /**
     * zoneType
     */
    @TableField("chain_zone_type")
    private String chainZoneType;

    /**
     * clusterId
     */
    @TableField("cluster_id")
    private Integer clusterId;

    /**
     * zoneId
     */
    @TableField("zone_id")
    private Integer zoneId;

    /**
     * 除发送方交易消耗的gas，单位Tgas
     */
    @TableField("lock_gas")
    private String lockGas;

    /**
     * 该账户所有已经销毁的TOP token，单位uTOP
     */
    @TableField("burned_token")
    private String burnedToken;

    /**
     * 解锁中的兑换gas的TOP token
     */
    @TableField("unlock_gas_staked")
    private String unlockGasStaked;

    /**
     * 扣除发送方交易保证金，单位uTOP
     */
    @TableField("lock_deposit_balance")
    private String lockDepositBalance;

    /**
     * 解锁中的兑换disk的TOP token
     */
    @TableField("unlock_disk_staked")
    private String unlockDiskStaked;

    /**
     * 新共识成功的交易的unit block高度
     */
    @TableField("latest_unit_height")
    private String latestUnitHeight;

    /**
     * 最新共识成功的交易xx64hash
     */
    @TableField("latest_tx_hash_xxhash64")
    private String latestTxHashXxhash64;

    /**
     * 兑换选票锁定的TOP token，单位uTOP
     */
    @TableField("vote_staked_token")
    private String voteStakedToken;

    /**
     * 该账户最新共识成功的交易序号，唯一
     */
    @TableField("nonce")
    private String nonce;

    /**
     * 该账户未使用选票数量
     */
    @TableField("unused_vote_amount")
    private String unusedVoteAmount;

    /**
     * 最新共识成功的交易hash
     */
    @TableField("latest_tx_hash")
    private String latestTxHash;

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
