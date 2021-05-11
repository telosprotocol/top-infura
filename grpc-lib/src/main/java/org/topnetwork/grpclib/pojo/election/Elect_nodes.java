package org.topnetwork.grpclib.pojo.election;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Elect_nodes {

    private String account;
    private BigInteger group_id;
    private String node_type;
    private String public_key;
    private BigInteger slot_id;
    private BigInteger stake;
    private BigInteger start_timer_height;
    private BigInteger timestamp;
    private BigInteger version;
    public void setAccount(String account) {
         this.account = account;
     }
     public String getAccount() {
         return account;
     }

    public void setGroup_id(BigInteger group_id) {
         this.group_id = group_id;
     }
     public BigInteger getGroup_id() {
         return group_id;
     }

    public void setNode_type(String node_type) {
         this.node_type = node_type;
     }
     public String getNode_type() {
         return node_type;
     }

    public void setPublic_key(String public_key) {
         this.public_key = public_key;
     }
     public String getPublic_key() {
         return public_key;
     }

    public void setSlot_id(BigInteger slot_id) {
         this.slot_id = slot_id;
     }
     public BigInteger getSlot_id() {
         return slot_id;
     }

    public void setStake(BigInteger stake) {
         this.stake = stake;
     }
     public BigInteger getStake() {
         return stake;
     }

    public void setStart_timer_height(BigInteger start_timer_height) {
         this.start_timer_height = start_timer_height;
     }
     public BigInteger getStart_timer_height() {
         return start_timer_height;
     }

    public void setTimestamp(BigInteger timestamp) {
         this.timestamp = timestamp;
     }
     public BigInteger getTimestamp() {
         return timestamp;
     }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }
}