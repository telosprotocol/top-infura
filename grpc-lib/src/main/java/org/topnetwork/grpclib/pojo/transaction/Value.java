package org.topnetwork.grpclib.pojo.transaction;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Value {

    private Original_tx_info original_tx_info;
    private Tx_consensus_state tx_consensus_state;

    public Original_tx_info getOriginal_tx_info() {
        return original_tx_info;
    }

    public void setOriginal_tx_info(Original_tx_info original_tx_info) {
        this.original_tx_info = original_tx_info;
    }

    public Tx_consensus_state getTx_consensus_state() {
        return tx_consensus_state;
    }

    public void setTx_consensus_state(Tx_consensus_state tx_consensus_state) {
        this.tx_consensus_state = tx_consensus_state;
    }


    @Override
    public String toString() {
        return "Value{" +
                "original_tx_info=" + original_tx_info +
                ", tx_consensus_state=" + tx_consensus_state +
                '}';
    }
}