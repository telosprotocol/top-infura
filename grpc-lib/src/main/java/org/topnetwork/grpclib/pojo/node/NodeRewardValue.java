package org.topnetwork.grpclib.pojo.node;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class NodeRewardValue implements Serializable{

	/**
	 * 节点奖励总额，单位uTOP。
	 */
	private BigInteger accumulated;

	private BigInteger accumulated_decimals;
	/**
	 * 上次领取奖励的时钟高度。
	 */
	private BigInteger last_claim_time;
	/**
	 * 未领取的奖励，单位uTOP。
	 */
	private BigInteger unclaimed;

	private BigInteger unclaimed_decimals;
	/**
	 * 发放奖励时钟高度
	 */
	private BigInteger issue_time;

	
}
