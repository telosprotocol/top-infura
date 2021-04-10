package org.topnetwork.grpclib.pojo.account;

import java.math.BigInteger;

/**
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AccountValue {

    private String account_addr;
    private BigInteger available_gas;
    private BigInteger balance;
    private BigInteger burned_token;
    private Integer cluster_id;
    private long created_time;
    private BigInteger unlock_disk_staked;
    private BigInteger disk_staked_token;
    private BigInteger gas_staked_token;
    private String latest_tx_hash;
    //合约code
    private String contract_code;
    //合约创建者
    private String contract_parent_account;
    private String latest_tx_hash_xxhash64;
    private Long latest_unit_height;
    private BigInteger lock_balance;
    private BigInteger lock_deposit_balance;
    private BigInteger lock_gas;
    private BigInteger nonce;
    private Integer group_id;
    private BigInteger unlock_gas_staked;
    private BigInteger total_gas;
    private BigInteger unused_vote_amount;
    private BigInteger vote_staked_token;
    private Integer zone_id;

    public String getAccount_addr() {
        return account_addr;
    }

    public void setAccount_addr(String account_addr) {
        this.account_addr = account_addr;
    }

    public BigInteger getAvailable_gas() {
        return available_gas;
    }

    public void setAvailable_gas(BigInteger available_gas) {
        this.available_gas = available_gas;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public BigInteger getBurned_token() {
        return burned_token;
    }

    public void setBurned_token(BigInteger burned_token) {
        this.burned_token = burned_token;
    }

    public Integer getCluster_id() {
        return cluster_id;
    }

    public void setCluster_id(Integer cluster_id) {
        this.cluster_id = cluster_id;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public BigInteger getDisk_staked_token() {
        return disk_staked_token;
    }

    public void setDisk_staked_token(BigInteger disk_staked_token) {
        this.disk_staked_token = disk_staked_token;
    }

    public BigInteger getGas_staked_token() {
        return gas_staked_token;
    }

    public void setGas_staked_token(BigInteger gas_staked_token) {
        this.gas_staked_token = gas_staked_token;
    }

    public String getLatest_tx_hash() {
        return latest_tx_hash;
    }

    public void setLatest_tx_hash(String latest_tx_hash) {
        this.latest_tx_hash = latest_tx_hash;
    }

    public String getLatest_tx_hash_xxhash64() {
        return latest_tx_hash_xxhash64;
    }

    public void setLatest_tx_hash_xxhash64(String latest_tx_hash_xxhash64) {
        this.latest_tx_hash_xxhash64 = latest_tx_hash_xxhash64;
    }

    public Long getLatest_unit_height() {
        return latest_unit_height;
    }

    public void setLatest_unit_height(Long latest_unit_height) {
        this.latest_unit_height = latest_unit_height;
    }

    public BigInteger getLock_balance() {
        return lock_balance;
    }

    public void setLock_balance(BigInteger lock_balance) {
        this.lock_balance = lock_balance;
    }

    public BigInteger getLock_deposit_balance() {
        return lock_deposit_balance;
    }

    public void setLock_deposit_balance(BigInteger lock_deposit_balance) {
        this.lock_deposit_balance = lock_deposit_balance;
    }

    public BigInteger getLock_gas() {
        return lock_gas;
    }

    public void setLock_gas(BigInteger lock_gas) {
        this.lock_gas = lock_gas;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public BigInteger getTotal_gas() {
        return total_gas;
    }

    public void setTotal_gas(BigInteger total_gas) {
        this.total_gas = total_gas;
    }

    public BigInteger getUnused_vote_amount() {
        return unused_vote_amount;
    }

    public void setUnused_vote_amount(BigInteger unused_vote_amount) {
        this.unused_vote_amount = unused_vote_amount;
    }

    public BigInteger getVote_staked_token() {
        return vote_staked_token;
    }

    public void setVote_staked_token(BigInteger vote_staked_token) {
        this.vote_staked_token = vote_staked_token;
    }

    public Integer getZone_id() {
        return zone_id;
    }

    public void setZone_id(Integer zone_id) {
        this.zone_id = zone_id;
    }

    public String getContract_code() {
        return contract_code;
    }

    public void setContract_code(String contract_code) {
        this.contract_code = contract_code;
    }

    public String getContract_parent_account() {
        return contract_parent_account;
    }

    public void setContract_parent_account(String contract_parent_account) {
        this.contract_parent_account = contract_parent_account;
    }
    
    public BigInteger getUnlock_disk_staked() {
		return unlock_disk_staked;
	}

	public void setUnlock_disk_staked(BigInteger unlock_disk_staked) {
		this.unlock_disk_staked = unlock_disk_staked;
	}

	public BigInteger getUnlock_gas_staked() {
		return unlock_gas_staked;
	}

	public void setUnlock_gas_staked(BigInteger unlock_gas_staked) {
		this.unlock_gas_staked = unlock_gas_staked;
	}

	@Override
    public String toString() {
        return "Value{" +
                "account_addr='" + account_addr + '\'' +
                ", available_gas=" + available_gas +
                ", balance=" + balance +
                ", burned_token=" + burned_token +
                ", cluster_id=" + cluster_id +
                ", created_time=" + created_time +
                ", disk_staked_token=" + disk_staked_token +
                ", gas_staked_token=" + gas_staked_token +
                ", latest_tx_hash='" + latest_tx_hash + '\'' +
                ", contract_code='" + contract_code + '\'' +
                ", contract_parent_account='" + contract_parent_account + '\'' +
                ", latest_tx_hash_xxhash64='" + latest_tx_hash_xxhash64 + '\'' +
                ", latest_unit_height=" + latest_unit_height +
                ", lock_balance=" + lock_balance +
                ", lock_deposit_balance=" + lock_deposit_balance +
                ", lock_gas=" + lock_gas +
                ", nonce=" + nonce +
                ", group_id=" + group_id +
                ", total_gas=" + total_gas +
                ", unused_vote_amount=" + unused_vote_amount +
                ", vote_staked_token=" + vote_staked_token +
                ", zone_id=" + zone_id +
                '}';
    }
}