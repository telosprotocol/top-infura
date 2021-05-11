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
    private Integer from_network_id;
    private String last_tx_hash;
    private BigInteger last_tx_nonce;
    private String note;
    private String parent_account;
    private Long send_timestamp;
    private Integer to_network_id;
    private Tx_action tx_action;
    private BigInteger tx_deposit;
    private Integer tx_expire_duration;
    private String tx_hash;
    private BigInteger tx_len;
    private Integer tx_random_nonce;
    private Integer tx_structure_version;
    private Integer tx_type;

    private Integer from_ledger_id;
    private Integer to_ledger_id;
    private BigInteger premium_price;
    private String tx_state;

}