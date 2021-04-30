package org.topnetwork.grpclib.pojo.transaction;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
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
    private BigInteger tx_len;
    private int tx_random_nonce;
    private int tx_structure_version;
    private int tx_type;

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