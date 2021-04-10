package org.topnetwork.analysis.bean;

import org.topnetwork.grpclib.enums.AccountTypeEnum;
import org.topnetwork.grpclib.enums.ChainZoneType;

import java.math.BigInteger;

public class Account {

	private AccountTypeEnum type;

    private String address;

    private BigInteger balance;

    private BigInteger lockBalance;

    private BigInteger txNum;

    private Integer shard;

    private BigInteger gasTotal;

    private BigInteger gasUnUse;

    private BigInteger diskStakedToken;

    private BigInteger gasStakedToken;

    private Long createTime;

    private ChainZoneType chainZoneType;
    
    private Integer clusterId;

    private Integer zoneId;
	
	private BigInteger burnedToken;
	
	private String latestTxHash;
	
	private String latestTxHashXxhash64;
	
	private BigInteger latestUnitHeight;
	
	private BigInteger lockDepositBalance;
	
	private BigInteger lockGas;
	
	private BigInteger nonce;
	
	private BigInteger unlockDiskStaked;
	
	private BigInteger unlockGasStaked;
	
	private BigInteger unusedVoteAmount;
	
	private BigInteger voteStakedToken;

    public AccountTypeEnum getType() {
        return type;
    }

    public void setType(AccountTypeEnum type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public BigInteger getLockBalance() {
        return lockBalance;
    }

    public void setLockBalance(BigInteger lockBalance) {
        this.lockBalance = lockBalance;
    }

    public BigInteger getTxNum() {
        return txNum;
    }

    public void setTxNum(BigInteger txNum) {
        this.txNum = txNum;
    }

    public Integer getShard() {
        return shard;
    }

    public void setShard(Integer shard) {
        this.shard = shard;
    }

    public BigInteger getGasTotal() {
        return gasTotal;
    }

    public void setGasTotal(BigInteger gasTotal) {
        this.gasTotal = gasTotal;
    }

    public BigInteger getGasUnUse() {
        return gasUnUse;
    }

    public void setGasUnUse(BigInteger gasUnUse) {
        this.gasUnUse = gasUnUse;
    }

    public BigInteger getDiskStakedToken() {
        return diskStakedToken;
    }

    public void setDiskStakedToken(BigInteger diskStakedToken) {
        this.diskStakedToken = diskStakedToken;
    }

    public BigInteger getGasStakedToken() {
        return gasStakedToken;
    }

    public void setGasStakedToken(BigInteger gasStakedToken) {
        this.gasStakedToken = gasStakedToken;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public ChainZoneType getChainZoneType() {
        return chainZoneType;
    }

    public void setChainZoneType(ChainZoneType chainZoneType) {
        this.chainZoneType = chainZoneType;
    }
    
    public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	
	public BigInteger getBurnedToken() {
		return burnedToken;
	}

	public void setBurnedToken(BigInteger burnedToken) {
		this.burnedToken = burnedToken;
	}

	public String getLatestTxHash() {
		return latestTxHash;
	}

	public void setLatestTxHash(String latestTxHash) {
		this.latestTxHash = latestTxHash;
	}

	public String getLatestTxHashXxhash64() {
		return latestTxHashXxhash64;
	}

	public void setLatestTxHashXxhash64(String latestTxHashXxhash64) {
		this.latestTxHashXxhash64 = latestTxHashXxhash64;
	}

	public BigInteger getLatestUnitHeight() {
		return latestUnitHeight;
	}

	public void setLatestUnitHeight(BigInteger latestUnitHeight) {
		this.latestUnitHeight = latestUnitHeight;
	}

	public BigInteger getLockDepositBalance() {
		return lockDepositBalance;
	}

	public void setLockDepositBalance(BigInteger lockDepositBalance) {
		this.lockDepositBalance = lockDepositBalance;
	}

	public BigInteger getLockGas() {
		return lockGas;
	}

	public void setLockGas(BigInteger lockGas) {
		this.lockGas = lockGas;
	}

	public BigInteger getNonce() {
		return nonce;
	}

	public void setNonce(BigInteger nonce) {
		this.nonce = nonce;
	}

	public BigInteger getUnlockDiskStaked() {
		return unlockDiskStaked;
	}

	public void setUnlockDiskStaked(BigInteger unlockDiskStaked) {
		this.unlockDiskStaked = unlockDiskStaked;
	}

	public BigInteger getUnlockGasStaked() {
		return unlockGasStaked;
	}

	public void setUnlockGasStaked(BigInteger unlockGasStaked) {
		this.unlockGasStaked = unlockGasStaked;
	}

	public BigInteger getUnusedVoteAmount() {
		return unusedVoteAmount;
	}

	public void setUnusedVoteAmount(BigInteger unusedVoteAmount) {
		this.unusedVoteAmount = unusedVoteAmount;
	}

	public BigInteger getVoteStakedToken() {
		return voteStakedToken;
	}

	public void setVoteStakedToken(BigInteger voteStakedToken) {
		this.voteStakedToken = voteStakedToken;
	}

	@Override
	public String toString() {
		return String.format(
				"Account [type=%s, address=%s, balance=%s, lockBalance=%s, txNum=%s, shard=%s, gasTotal=%s, gasUnUse=%s, diskStakedToken=%s, gasStakedToken=%s, createTime=%s, chainZoneType=%s, clusterId=%s, zoneId=%s, burnedToken=%s, latestTxHash=%s, latestTxHashXxhash64=%s, latestUnitHeight=%s, lockDepositBalance=%s, lockGas=%s, nonce=%s, unlockDiskStaked=%s, unlockGasStaked=%s, unusedVoteAmount=%s, voteStakedToken=%s]",
				type, address, balance, lockBalance, txNum, shard, gasTotal, gasUnUse, diskStakedToken, gasStakedToken,
				createTime, chainZoneType, clusterId, zoneId, burnedToken, latestTxHash,
				latestTxHashXxhash64, latestUnitHeight, lockDepositBalance, lockGas, nonce, unlockDiskStaked,
				unlockGasStaked, unusedVoteAmount, voteStakedToken);
	}

}
