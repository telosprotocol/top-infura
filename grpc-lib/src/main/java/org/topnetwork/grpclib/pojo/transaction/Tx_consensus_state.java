package org.topnetwork.grpclib.pojo.transaction;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Tx_consensus_state {

    private Confirm_unit_info confirm_unit_info;
    private UnitInfo recv_unit_info;
    private UnitInfo send_unit_info;

    public void setConfirm_unit_info(Confirm_unit_info confirm_unit_info) {
         this.confirm_unit_info = confirm_unit_info;
     }
     public Confirm_unit_info getConfirm_unit_info() {
         return confirm_unit_info;
     }

    public UnitInfo getRecv_unit_info() {
        return recv_unit_info;
    }

    public void setRecv_unit_info(UnitInfo recv_unit_info) {
        this.recv_unit_info = recv_unit_info;
    }

    public UnitInfo getSend_unit_info() {
        return send_unit_info;
    }

    public void setSend_unit_info(UnitInfo send_unit_info) {
        this.send_unit_info = send_unit_info;
    }

    @Override
    public String toString() {
        return "Tx_consensus_state{" +
                "confirm_unit_info=" + confirm_unit_info +
                ", recv_unit_info=" + recv_unit_info +
                ", send_unit_info=" + send_unit_info +
                '}';
    }
}