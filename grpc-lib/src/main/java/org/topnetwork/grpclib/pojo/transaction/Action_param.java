package org.topnetwork.grpclib.pojo.transaction;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Action_param {

    /**
     * 执行合约的函数名 xaction_run_contract
     */
    private String func_name;
    /**
     * 执行合约的参数 xaction_run_contract
     */
    private String paras;
    /**
     * 账号地址 xaction_create_user_account
     */
    private String address;
    /**
     * xaction_asset_out 币名
     */
    private String token_name;
    /**
     * xaction_asset_out 转出币的数量
     */
    private BigInteger amount;
    /**
     * xaction_deploy_contract 币一次合约调用中，合约账户愿
     */
    private BigInteger tgas_limit;
    /**
     * xaction_deploy_contract 合约代码
     */
    private String code;
    /**
     * xaction_pledge_token_vote 兑票数
     */
    private String vote_num;
    /**
     * xaction_pledge_token_vote 票的锁定时长（天）
     */
    private String lock_duration;
    /**
     * xaction_set_account_keys 属性的key
     */
    private String account_key;
    /**
     * xaction_set_account_keys 设置的值
     */
    private String key_value;
    /**
     * xaction_lock_account_token 版本
     */
    private String version;
    /**
     * xaction_lock_account_token 解锁类型
     */
    private String unlock_type;
    /**
     * xaction_lock_account_token 解锁类型对应的值
     */
    private String unlock_values;
    /**
     * xaction_lock_account_token 序列化的锁定上下文
     */
    private String params;
    /**
     * xaction_unlock_account_token 锁定交易的hash
     */
    private String lock_tran_hash;
    /**
     * xaction_unlock_account_token 签名
     */
    private String signatures;
    /**
     * xaction_alias_name 别名
     */
    private String name;

    public String getFunc_name() {
        return func_name;
    }


    @Override
    public String toString() {
        return "Action_param{" +
                "func_name='" + func_name + '\'' +
                ", paras='" + paras + '\'' +
                ", address='" + address + '\'' +
                ", token_name='" + token_name + '\'' +
                ", amount='" + amount + '\'' +
                ", tgas_limit='" + tgas_limit + '\'' +
                ", code='" + code + '\'' +
                ", vote_num='" + vote_num + '\'' +
                ", lock_duration='" + lock_duration + '\'' +
                ", account_key='" + account_key + '\'' +
                ", key_value='" + key_value + '\'' +
                ", version='" + version + '\'' +
                ", unlock_type='" + unlock_type + '\'' +
                ", unlock_values='" + unlock_values + '\'' +
                ", params='" + params + '\'' +
                ", lock_tran_hash='" + lock_tran_hash + '\'' +
                ", signatures='" + signatures + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}