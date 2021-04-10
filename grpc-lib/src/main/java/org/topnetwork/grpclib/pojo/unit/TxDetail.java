package org.topnetwork.grpclib.pojo.unit;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TxDetail {

    private int send_tx_lock_tgas;
    private String tx_consensus_phase;
    private int tx_exec_status;
    private int used_disk;
    private int used_gas;
    private int used_tx_deposit;
    public void setSend_tx_lock_tgas(int send_tx_lock_tgas) {
         this.send_tx_lock_tgas = send_tx_lock_tgas;
     }
     public int getSend_tx_lock_tgas() {
         return send_tx_lock_tgas;
     }

    public void setTx_consensus_phase(String tx_consensus_phase) {
         this.tx_consensus_phase = tx_consensus_phase;
     }
     public String getTx_consensus_phase() {
         return tx_consensus_phase;
     }

    public void setTx_exec_status(int tx_exec_status) {
         this.tx_exec_status = tx_exec_status;
     }
     public int getTx_exec_status() {
         return tx_exec_status;
     }

    public void setUsed_disk(int used_disk) {
         this.used_disk = used_disk;
     }
     public int getUsed_disk() {
         return used_disk;
     }

    public void setUsed_gas(int used_gas) {
         this.used_gas = used_gas;
     }
     public int getUsed_gas() {
         return used_gas;
     }

    public void setUsed_tx_deposit(int used_tx_deposit) {
         this.used_tx_deposit = used_tx_deposit;
     }
     public int getUsed_tx_deposit() {
         return used_tx_deposit;
     }

}