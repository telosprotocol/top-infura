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
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /******************tx info***********************/
    @TableField("authorization")
    private String authorization;

    @TableField("challenge_proof")
    private String challengeProof;

    @TableField("edge_nodeid")
    private String edgeNodeId;

    @TableField("ext")
    private String ext;

    @TableField("from_network_id")
    private Integer fromNetworkId;

    @TableField("to_network_id")
    private Integer toNetworkId;
    @TableField("parent_account")
    private String parentAccount;

    @TableField("send_timestamp")
    private Long sendTimestamp;


    @TableField("tx_expire_duration")
    private Integer txExpireDuration;

    @TableField("tx_random_nonce")
    private Integer txRandomNonce;

    @TableField("tx_structure_version")
    private Integer txStructureVersion;

    @TableField("last_tx_hash")
    private String lastTxHash;

    @TableField("tx_state")
    private String txState;

    @TableField("from_ledger_id")
    private Integer fromLedgerId;

    @TableField("to_ledger_id")
    private Integer toLedgerId;

    @TableField("premium_price")
    private BigInteger premiumPrice;

    /******************tx info end***********************/



    /******************send unit***********************/
    @TableField("send_unit_tx_fee")
    private BigInteger sendUnitTxFee;

    @TableField("send_unit_height")
    private Long sendUnitHeight;

    @TableField("send_unit_hash")
    private String sendUnitHash;


    /**
     * send_used_deposit
     */
    @TableField("send_unit_used_deposit")
    private BigInteger sendUnitUsedDeposit;

    /**
     * send_used_gas
     */
    @TableField("send_unit_used_gas")
    private BigInteger sendUnitUsedGas;

    /**
     * send_used_disk
     */
    @TableField("send_unit_used_disk")
    private BigInteger sendUnitUsedDisk;
    /******************send unit end***********************/



    /******************recv unit***********************/

    @TableField("recv_unit_hash")
    private String recvUnitHash;

    @TableField("recv_unit_height")
    private Long recvUnitHeight;

    /**
     * recv_used_deposit
     */
    @TableField("recv_unit_used_deposit")
    private BigInteger recvUnitUsedDeposit;

    /**
     * recv_used_gas
     */
    @TableField("recv_unit_used_gas")
    private BigInteger recvUnitUsedGas;

    /**
     * recv_used_disk
     */
    @TableField("recv_unit_used_disk")
    private BigInteger recvUnitUsedDisk;
    /******************recv end***********************/

    /******************confirm unit***********************/
    @TableField("confirm_unit_hash")
    private String confirmUnitHash;

    @TableField("confirm_unit_exec_status")
    private String confirmUnitExecStatus;

    @TableField("confirm_unit_height")
    private Long confirmUnitHeight;

    @TableField("confirm_unit_tx_exec_status")
    private String confirmUnitTxExecStatus;

    @TableField("confirm_unit_used_deposit")
    private BigInteger confirmUnitUsedDeposit;

    @TableField("confirm_unit_used_disk")
    private BigInteger confirmUnitUsedDisk;

    @TableField("confirm_unit_used_gas")
    private BigInteger confirmUnitUsedGas;

    /******************confirm unit end***********************/




    /**************sender action*****************/

    @TableField("sender_action_authorization")
    private String senderActionAutorization;

    @TableField("sender_action_ext")
    private String senderActionExt;

    @TableField("sender_action_hash")
    private Long senderActionHash;

    @TableField("sender_action_size")
    private Long senderActionSize;

    @TableField("sender_action_type")
    private Integer senderActionType;

    @TableField("sender_action_account_addr")
    private String senderActionAccountAddr;

    /**************sender action param*****************/

    @TableField("sender_action_param_func_name")
    private String senderActionParamFuncName;

    @TableField("sender_action_param_paras")
    private String senderActionParamParas;

    @TableField("sender_acton_param_address")
    private String senderActionParamAddress;

    @TableField("sender_action_param_token_name")
    private String senderActionParamTokenName;

    @TableField("sender_action_param_amount")
    private BigInteger senderActionParamAmount;

    @TableField("sender_action_param_tgas_limit")
    private BigInteger senderActionParamTgasLimit;

    @TableField("sender_action_param_code")
    private String senderActionParamCode;

    @TableField("sender_action_param_vote_num")
    private String senderActionParamVoteNum;

    @TableField("sender_action_param_lock_duration")
    private String senderActionParamLockDuration;

    @TableField("sender_action_param_account_key")
    private String senderActionParamAccountKey;

    @TableField("sender_action_param_key_value")
    private String senderActionParamKeyValue;

    @TableField("sender_action_param_version")
    private String senderActionParamVersion;

    @TableField("sender_action_param_unlock_type")
    private String senderActionParamUnlockType;

    @TableField("sender_action_param_unlock_values")
    private String senderActionParamUnlockValues;

    @TableField("sender_action_param_params")
    private String senderActionParamParams;

    @TableField("sender_action_param_lock_tran_hash")
    private String senderActionParamLockTranHash;

    @TableField("sender_action_param_signatures")
    private String senderActionParamSignatures;

    @TableField("sender_action_param_name")
    private String senderActionParamName;

    /**************sender action end*****************/


    /**************receiver action *****************/
    @TableField("receiver_action_authorization")
    private String receiverActionAutorization;

    @TableField("receiver_action_ext")
    private String receiverActionExt;

    @TableField("receiver_action_hash")
    private Long receiverActionHash;

    @TableField("receiver_action_size")
    private Long receiverActionSize;

    @TableField("receiver_action_type")
    private Integer receiverActionType;

    @TableField("receiver_action_account_addr")
    private String receiverActionAccountAddr;

    /**************receiver action param*****************/
    @TableField("receiver_action_param_func_name")
    private String receiverActionParamFuncName;

    @TableField("receiver_action_param_paras")
    private String receiverActionParamParas;

    @TableField("receiver_acton_param_address")
    private String receiverActionParamAddress;

    @TableField("receiver_action_param_token_name")
    private String receiverActionParamTokenName;

    @TableField("receiver_action_param_amount")
    private BigInteger receiverActionParamAmount;

    @TableField("receiver_action_param_tgas_limit")
    private BigInteger receiverActionParamTgasLimit;

    @TableField("receiver_action_param_code")
    private String receiverActionParamCode;

    @TableField("receiver_action_param_vote_num")
    private String receiverActionParamVoteNum;

    @TableField("receiver_action_param_lock_duration")
    private String receiverActionParamLockDuration;

    @TableField("receiver_action_param_account_key")
    private String receiverActionParamAccountKey;

    @TableField("receiver_action_param_key_value")
    private String receiverActionParamKeyValue;

    @TableField("receiver_action_param_version")
    private String receiverActionParamVersion;

    @TableField("receiver_action_param_unlock_type")
    private String receiverActionParamUnlockType;

    @TableField("receiver_action_param_unlock_values")
    private String receiverActionParamUnlockValues;

    @TableField("receiver_action_param_params")
    private String receiverActionParamParams;

    @TableField("receiver_action_param_lock_tran_hash")
    private String receiverActionParamLockTranHash;

    @TableField("receiver_action_param_signatures")
    private String receiverActionParamSignatures;

    @TableField("receiver_action_param_name")
    private String receiverActionParamName;

    /**************receiver action end *****************/

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
