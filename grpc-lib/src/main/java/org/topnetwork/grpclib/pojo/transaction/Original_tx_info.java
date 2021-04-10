package org.topnetwork.grpclib.pojo.transaction;

import java.math.BigInteger;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Original_tx_info {

    private String authorization;
    private String challenge_proof;
    private String edge_nodeid;
    private String ext;
    private int from_network_id;
    private BigInteger last_tx_hash;
    private BigInteger last_tx_nonce;
    private String note;
    private String parent_account;
    private long send_timestamp;
    private int to_network_id;
    private Tx_action tx_action;
    private BigInteger tx_deposit;
    private int tx_expire_duration;
    private String tx_hash;
    private int tx_len;
    private int tx_random_nonce;
    private int tx_structure_version;
    private int tx_type;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getChallenge_proof() {
        return challenge_proof;
    }

    public void setChallenge_proof(String challenge_proof) {
        this.challenge_proof = challenge_proof;
    }

    public String getEdge_nodeid() {
        return edge_nodeid;
    }

    public void setEdge_nodeid(String edge_nodeid) {
        this.edge_nodeid = edge_nodeid;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getFrom_network_id() {
        return from_network_id;
    }

    public void setFrom_network_id(int from_network_id) {
        this.from_network_id = from_network_id;
    }

    public BigInteger getLast_tx_hash() {
        return last_tx_hash;
    }

    public void setLast_tx_hash(BigInteger last_tx_hash) {
        this.last_tx_hash = last_tx_hash;
    }

    public BigInteger getLast_tx_nonce() {
        return last_tx_nonce;
    }

    public void setLast_tx_nonce(BigInteger last_tx_nonce) {
        this.last_tx_nonce = last_tx_nonce;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getParent_account() {
        return parent_account;
    }

    public void setParent_account(String parent_account) {
        this.parent_account = parent_account;
    }

    public long getSend_timestamp() {
        return send_timestamp;
    }

    public void setSend_timestamp(long send_timestamp) {
        this.send_timestamp = send_timestamp;
    }

    public int getTo_network_id() {
        return to_network_id;
    }

    public void setTo_network_id(int to_network_id) {
        this.to_network_id = to_network_id;
    }

    public Tx_action getTx_action() {
        return tx_action;
    }

    public void setTx_action(Tx_action tx_action) {
        this.tx_action = tx_action;
    }

    public BigInteger getTx_deposit() {
        return tx_deposit;
    }

    public void setTx_deposit(BigInteger tx_deposit) {
        this.tx_deposit = tx_deposit;
    }

    public int getTx_expire_duration() {
        return tx_expire_duration;
    }

    public void setTx_expire_duration(int tx_expire_duration) {
        this.tx_expire_duration = tx_expire_duration;
    }

    public String getTx_hash() {
        return tx_hash;
    }

    public void setTx_hash(String tx_hash) {
        this.tx_hash = tx_hash;
    }

    public int getTx_len() {
        return tx_len;
    }

    public void setTx_len(int tx_len) {
        this.tx_len = tx_len;
    }

    public int getTx_random_nonce() {
        return tx_random_nonce;
    }

    public void setTx_random_nonce(int tx_random_nonce) {
        this.tx_random_nonce = tx_random_nonce;
    }

    public int getTx_structure_version() {
        return tx_structure_version;
    }

    public void setTx_structure_version(int tx_structure_version) {
        this.tx_structure_version = tx_structure_version;
    }

    public int getTx_type() {
        return tx_type;
    }

    public void setTx_type(int tx_type) {
        this.tx_type = tx_type;
    }

    @Override
    public String toString() {
        return "Original_tx_info{" +
                "authorization='" + authorization + '\'' +
                ", challenge_proof='" + challenge_proof + '\'' +
                ", edge_nodeid='" + edge_nodeid + '\'' +
                ", ext='" + ext + '\'' +
                ", from_network_id=" + from_network_id +
                ", last_tx_hash=" + last_tx_hash +
                ", last_tx_nonce=" + last_tx_nonce +
                ", note='" + note + '\'' +
                ", parent_account='" + parent_account + '\'' +
                ", send_timestamp=" + send_timestamp +
                ", to_network_id=" + to_network_id +
                ", tx_action=" + tx_action +
                ", tx_deposit=" + tx_deposit +
                ", tx_expire_duration=" + tx_expire_duration +
                ", tx_hash='" + tx_hash + '\'' +
                ", tx_len=" + tx_len +
                ", tx_random_nonce=" + tx_random_nonce +
                ", tx_structure_version=" + tx_structure_version +
                ", tx_type=" + tx_type +
                '}';
    }
}