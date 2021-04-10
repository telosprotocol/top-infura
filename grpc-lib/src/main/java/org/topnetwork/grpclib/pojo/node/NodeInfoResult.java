package org.topnetwork.grpclib.pojo.node;

import java.io.Serializable;
import java.math.BigInteger;

public class NodeInfoResult implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String account_addr;
    private String auditor_credit;
    private BigInteger auditor_stake;
    private BigInteger dividend_ratio;
    private String network_id;
    private BigInteger node_deposit;
    private String node_sign_key;
    private String nodename;
    private String registered_node_type;
    private String validator_credit;
    private BigInteger validator_stake;
    private BigInteger vote_amount;
    private BigInteger rec_stake;
    private BigInteger zec_stake;

    public String getAccount_addr() {
        return account_addr;
    }

    public void setAccount_addr(String account_addr) {
        this.account_addr = account_addr;
    }

    public String getAuditor_credit() {
        return auditor_credit;
    }

    public void setAuditor_credit(String auditor_credit) {
        this.auditor_credit = auditor_credit;
    }

    public BigInteger getAuditor_stake() {
        return auditor_stake;
    }

    public void setAuditor_stake(BigInteger auditor_stake) {
        this.auditor_stake = auditor_stake;
    }

    public BigInteger getDividend_ratio() {
        return dividend_ratio;
    }

    public void setDividend_ratio(BigInteger dividend_ratio) {
        this.dividend_ratio = dividend_ratio;
    }

    public String getNetwork_id() {
        return network_id;
    }

    public void setNetwork_id(String network_id) {
        this.network_id = network_id;
    }

    public BigInteger getNode_deposit() {
        return node_deposit;
    }

    public void setNode_deposit(BigInteger node_deposit) {
        this.node_deposit = node_deposit;
    }

    public String getNode_sign_key() {
        return node_sign_key;
    }

    public void setNode_sign_key(String node_sign_key) {
        this.node_sign_key = node_sign_key;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getRegistered_node_type() {
        return registered_node_type;
    }

    public void setRegistered_node_type(String registered_node_type) {
        this.registered_node_type = registered_node_type;
    }

    public String getValidator_credit() {
        return validator_credit;
    }

    public void setValidator_credit(String validator_credit) {
        this.validator_credit = validator_credit;
    }

    public BigInteger getValidator_stake() {
        return validator_stake;
    }

    public void setValidator_stake(BigInteger validator_stake) {
        this.validator_stake = validator_stake;
    }

    public BigInteger getVote_amount() {
        return vote_amount;
    }

    public void setVote_amount(BigInteger vote_amount) {
        this.vote_amount = vote_amount;
    }

    public BigInteger getRec_stake() {
        return rec_stake;
    }

    public void setRec_stake(BigInteger rec_stake) {
        this.rec_stake = rec_stake;
    }

    public BigInteger getZec_stake() {
        return zec_stake;
    }

    public void setZec_stake(BigInteger zec_stake) {
        this.zec_stake = zec_stake;
    }

    @Override
    public String toString() {
        return "NodeInfoResult{" +
                "account_addr='" + account_addr + '\'' +
                ", auditor_credit='" + auditor_credit + '\'' +
                ", auditor_stake=" + auditor_stake +
                ", dividend_ratio=" + dividend_ratio +
                ", network_id='" + network_id + '\'' +
                ", node_deposit=" + node_deposit +
                ", node_sign_key='" + node_sign_key + '\'' +
                ", nodename='" + nodename + '\'' +
                ", registered_node_type='" + registered_node_type + '\'' +
                ", validator_credit='" + validator_credit + '\'' +
                ", validator_stake=" + validator_stake +
                ", vote_amount=" + vote_amount +
                ", rec_stake=" + rec_stake +
                ", zec_stake=" + zec_stake +
                '}';
    }
}
