package org.topnetwork.analysis.bean;

import org.topnetwork.grpclib.enums.ChainZoneType;
import org.topnetwork.grpclib.enums.TxTypeEnum;

import java.math.BigInteger;

public class Transaction {
    private String hash;
    private TxTypeEnum txType;
    private Long timestamp;
    private String from;
    private String to;
    private String amount;
	private String note;
	private BigInteger last_tx_nonce;
	private BigInteger tx_len;
    private String status;
    private Integer shardFrom;
    private Integer shardTo;
	private BigInteger used_deposit;
    private Long gasUsed;
    private Long diskUsed;
	private BigInteger txDeposit;
	private ChainZoneType chainZoneType;

	private String receiver_action_param;
	private String sender_action_param;

	/**
	 * 执行合约的函数名 xaction_run_contract
	 */
	private String funcName;
	/**
	 * 执行合约的参数 xaction_run_contract
	 */
	private String para;
	/**
	 * 账号地址 xaction_create_user_account
	 */
	private String address;
	/**
	 * xaction_asset_out 币名
	 */
	private String tokenName;
	/**
	 * xaction_deploy_contract 一次合约调用中，
	 * 合约账户愿意支付的最大tgas
	 */
	private String tgasLimit;
	/**
	 * xaction_deploy_contract 合约代码
	 */
	private String code;
	/**
	 * xaction_pledge_token_vote 兑票数
	 */
	private String voteNum;
	/**
	 * xaction_pledge_token_vote 票的锁定时长（天）
	 */
	private String lockDuration;
	/**
	 * xaction_set_account_keys 属性的key
	 */
	private String accountKey;
	/**
	 * xaction_set_account_keys 设置的值
	 */
	private String keyValue;
	/**
	 * xaction_lock_account_token 版本
	 */
	private String version;
	/**
	 * xaction_lock_account_token 解锁类型
	 */
	private String unlockType;
	/**
	 * xaction_lock_account_token 解锁类型对应的值
	 */
	private String unlockValues;
	/**
	 * xaction_lock_account_token 序列化的锁定上下文
	 */
	private String params;
	/**
	 * xaction_unlock_account_token 锁定交易的hash
	 */
	private String lockTranHash;
	/**
	 * xaction_unlock_account_token 签名
	 */
	private String signatures;
	/**
	 * xaction_alias_name 别名
	 */
	private String name;

	private BigInteger recv_used_deposit;

	private BigInteger recv_used_gas;

	private BigInteger recv_used_disk;

	private BigInteger send_used_deposit;

	private BigInteger send_used_gas;

	private BigInteger send_used_disk;
	
	private BigInteger tx_fee;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public TxTypeEnum getTxType() {
		return txType;
	}

	public void setTxType(TxTypeEnum txType) {
		this.txType = txType;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigInteger getLast_tx_nonce() {
		return last_tx_nonce;
	}

	public void setLast_tx_nonce(BigInteger last_tx_nonce) {
		this.last_tx_nonce = last_tx_nonce;
	}

	public BigInteger getTx_len() {
		return tx_len;
	}

	public void setTx_len(BigInteger tx_len) {
		this.tx_len = tx_len;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getShardFrom() {
		return shardFrom;
	}

	public void setShardFrom(Integer shardFrom) {
		this.shardFrom = shardFrom;
	}

	public Integer getShardTo() {
		return shardTo;
	}

	public void setShardTo(Integer shardTo) {
		this.shardTo = shardTo;
	}

	public BigInteger getUsed_deposit() {
		return used_deposit;
	}

	public void setUsed_deposit(BigInteger used_deposit) {
		this.used_deposit = used_deposit;
	}

	public Long getGasUsed() {
		return gasUsed;
	}

	public void setGasUsed(Long gasUsed) {
		this.gasUsed = gasUsed;
	}

	public Long getDiskUsed() {
		return diskUsed;
	}

	public void setDiskUsed(Long diskUsed) {
		this.diskUsed = diskUsed;
	}

	public BigInteger getTxDeposit() {
		return txDeposit;
	}

	public void setTxDeposit(BigInteger txDeposit) {
		this.txDeposit = txDeposit;
	}

	public ChainZoneType getChainZoneType() {
		return chainZoneType;
	}

	public void setChainZoneType(ChainZoneType chainZoneType) {
		this.chainZoneType = chainZoneType;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getTgasLimit() {
		return tgasLimit;
	}

	public void setTgasLimit(String tgasLimit) {
		this.tgasLimit = tgasLimit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(String voteNum) {
		this.voteNum = voteNum;
	}

	public String getLockDuration() {
		return lockDuration;
	}

	public void setLockDuration(String lockDuration) {
		this.lockDuration = lockDuration;
	}

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUnlockType() {
		return unlockType;
	}

	public void setUnlockType(String unlockType) {
		this.unlockType = unlockType;
	}

	public String getUnlockValues() {
		return unlockValues;
	}

	public void setUnlockValues(String unlockValues) {
		this.unlockValues = unlockValues;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getLockTranHash() {
		return lockTranHash;
	}

	public void setLockTranHash(String lockTranHash) {
		this.lockTranHash = lockTranHash;
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

	public BigInteger getRecv_used_deposit() {
		return recv_used_deposit;
	}

	public void setRecv_used_deposit(BigInteger recv_used_deposit) {
		this.recv_used_deposit = recv_used_deposit;
	}

	public BigInteger getRecv_used_gas() {
		return recv_used_gas;
	}

	public void setRecv_used_gas(BigInteger recv_used_gas) {
		this.recv_used_gas = recv_used_gas;
	}

	public BigInteger getRecv_used_disk() {
		return recv_used_disk;
	}

	public void setRecv_used_disk(BigInteger recv_used_disk) {
		this.recv_used_disk = recv_used_disk;
	}

	public BigInteger getSend_used_deposit() {
		return send_used_deposit;
	}

	public void setSend_used_deposit(BigInteger send_used_deposit) {
		this.send_used_deposit = send_used_deposit;
	}

	public BigInteger getSend_used_gas() {
		return send_used_gas;
	}

	public void setSend_used_gas(BigInteger send_used_gas) {
		this.send_used_gas = send_used_gas;
	}

	public BigInteger getSend_used_disk() {
		return send_used_disk;
	}

	public void setSend_used_disk(BigInteger send_used_disk) {
		this.send_used_disk = send_used_disk;
	}

	public String getReceiver_action_param() {
		return receiver_action_param;
	}

	public void setReceiver_action_param(String receiver_action_param) {
		this.receiver_action_param = receiver_action_param;
	}

	public String getSender_action_param() {
		return sender_action_param;
	}

	public void setSender_action_param(String sender_action_param) {
		this.sender_action_param = sender_action_param;
	}
	

	public BigInteger getTx_fee() {
		return tx_fee;
	}

	public void setTx_fee(BigInteger tx_fee) {
		this.tx_fee = tx_fee;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"hash='" + hash + '\'' +
				", txType=" + txType +
				", timestamp=" + timestamp +
				", from='" + from + '\'' +
				", to='" + to + '\'' +
				", amount='" + amount + '\'' +
				", note='" + note + '\'' +
				", last_tx_nonce=" + last_tx_nonce +
				", tx_len=" + tx_len +
				", status='" + status + '\'' +
				", shardFrom=" + shardFrom +
				", shardTo=" + shardTo +
				", used_deposit=" + used_deposit +
				", gasUsed=" + gasUsed +
				", diskUsed=" + diskUsed +
				", txDeposit=" + txDeposit +
				", chainZoneType=" + chainZoneType +
				", receiver_action_param='" + receiver_action_param + '\'' +
				", sender_action_param='" + sender_action_param + '\'' +
				", funcName='" + funcName + '\'' +
				", para='" + para + '\'' +
				", address='" + address + '\'' +
				", tokenName='" + tokenName + '\'' +
				", tgasLimit='" + tgasLimit + '\'' +
				", code='" + code + '\'' +
				", voteNum='" + voteNum + '\'' +
				", lockDuration='" + lockDuration + '\'' +
				", accountKey='" + accountKey + '\'' +
				", keyValue='" + keyValue + '\'' +
				", version='" + version + '\'' +
				", unlockType='" + unlockType + '\'' +
				", unlockValues='" + unlockValues + '\'' +
				", params='" + params + '\'' +
				", lockTranHash='" + lockTranHash + '\'' +
				", signatures='" + signatures + '\'' +
				", name='" + name + '\'' +
				", recv_used_deposit=" + recv_used_deposit +
				", recv_used_gas=" + recv_used_gas +
				", recv_used_disk=" + recv_used_disk +
				", send_used_deposit=" + send_used_deposit +
				", send_used_gas=" + send_used_gas +
				", send_used_disk=" + send_used_disk +
				", tx_fee=" + tx_fee +
				'}';
	}
}
