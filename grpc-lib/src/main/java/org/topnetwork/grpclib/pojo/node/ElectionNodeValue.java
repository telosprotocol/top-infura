/**
  * Copyright 2020 bejson.com 
  */
package org.topnetwork.grpclib.pojo.node;

public class ElectionNodeValue {

	private String group_id;

	private String round;

	private int stake;

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public void setStake(int stake) {
		this.stake = stake;
	}

	public int getStake() {
		return stake;
	}

	@Override
	public String toString() {
		return String.format("ElectionNodeValue [group_id=%s, round=%s, stake=%s]", group_id, round, stake);
	}
     

}