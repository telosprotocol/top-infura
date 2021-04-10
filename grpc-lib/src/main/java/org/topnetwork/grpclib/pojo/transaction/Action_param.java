package org.topnetwork.grpclib.pojo.transaction;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
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
    private String amount;
    /**
     * xaction_deploy_contract 币一次合约调用中，合约账户愿
     */
    private String tgas_limit;
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

    public void setFunc_name(String func_name) {
        this.func_name = func_name;
    }

    public String getParas() {
        return paras;
    }

    public void setParas(String paras) {
        this.paras = paras;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken_name() {
        return token_name;
    }

    public void setToken_name(String token_name) {
        this.token_name = token_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTgas_limit() {
        return tgas_limit;
    }

    public void setTgas_limit(String tgas_limit) {
        this.tgas_limit = tgas_limit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVote_num() {
        return vote_num;
    }

    public void setVote_num(String vote_num) {
        this.vote_num = vote_num;
    }

    public String getLock_duration() {
        return lock_duration;
    }

    public void setLock_duration(String lock_duration) {
        this.lock_duration = lock_duration;
    }

    public String getAccount_key() {
        return account_key;
    }

    public void setAccount_key(String account_key) {
        this.account_key = account_key;
    }

    public String getKey_value() {
        return key_value;
    }

    public void setKey_value(String key_value) {
        this.key_value = key_value;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUnlock_type() {
        return unlock_type;
    }

    public void setUnlock_type(String unlock_type) {
        this.unlock_type = unlock_type;
    }

    public String getUnlock_values() {
        return unlock_values;
    }

    public void setUnlock_values(String unlock_values) {
        this.unlock_values = unlock_values;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getLock_tran_hash() {
        return lock_tran_hash;
    }

    public void setLock_tran_hash(String lock_tran_hash) {
        this.lock_tran_hash = lock_tran_hash;
    }

    public String getSignatures() {
        return signatures;
    }

    public void setSignatures(String signatures) {
        this.signatures = signatures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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