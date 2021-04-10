package org.topnetwork.grpclib.pojo.node;

import java.io.Serializable;
import java.math.BigInteger;

public class QueryNodeRewardValue implements Serializable{

	/**
	 * 节点奖励总额，单位uTOP。
	 */
	private BigInteger accumulated;
	/**
	 * 上次领取奖励的时钟高度。
	 */
	private BigInteger last_claim_time;
	/**
	 * 未领取的奖励，单位uTOP。
	 */
	private BigInteger unclaimed;
	/**
	 * 发放奖励时钟高度
	 */
	private BigInteger issue_time;

	public BigInteger getAccumulated() {
		return accumulated;
	}

	public void setAccumulated(BigInteger accumulated) {
		this.accumulated = accumulated;
	}

	public BigInteger getLast_claim_time() {
		return last_claim_time;
	}

	public void setLast_claim_time(BigInteger last_claim_time) {
		this.last_claim_time = last_claim_time;
	}

	public BigInteger getUnclaimed() {
		return unclaimed;
	}

	public void setUnclaimed(BigInteger unclaimed) {
		this.unclaimed = unclaimed;
	}

	public BigInteger getIssue_time() {
		return issue_time;
	}

	public void setIssue_time(BigInteger issue_time) {
		this.issue_time = issue_time;
	}
	
}
