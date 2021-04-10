package org.topnetwork.grpclib.pojo.stream;

import java.io.Serializable;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class LightUnitInput implements Serializable {

    private int is_contract_create;
    private int last_tx_nonce;
    private int sender_tx_locked_gas;
    private String tx_consensus_phase;
    public void setIs_contract_create(int is_contract_create) {
         this.is_contract_create = is_contract_create;
     }
     public int getIs_contract_create() {
         return is_contract_create;
     }

    public void setLast_tx_nonce(int last_tx_nonce) {
         this.last_tx_nonce = last_tx_nonce;
     }
     public int getLast_tx_nonce() {
         return last_tx_nonce;
     }

    public void setSender_tx_locked_gas(int sender_tx_locked_gas) {
         this.sender_tx_locked_gas = sender_tx_locked_gas;
     }
     public int getSender_tx_locked_gas() {
         return sender_tx_locked_gas;
     }

    public void setTx_consensus_phase(String tx_consensus_phase) {
         this.tx_consensus_phase = tx_consensus_phase;
     }
     public String getTx_consensus_phase() {
         return tx_consensus_phase;
     }

    @Override
    public String toString() {
        return "LightUnitInput{" +
                "is_contract_create=" + is_contract_create +
                ", last_tx_nonce=" + last_tx_nonce +
                ", sender_tx_locked_gas=" + sender_tx_locked_gas +
                ", tx_consensus_phase='" + tx_consensus_phase + '\'' +
                '}';
    }
}