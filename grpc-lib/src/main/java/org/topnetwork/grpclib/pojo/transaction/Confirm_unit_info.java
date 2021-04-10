package org.topnetwork.grpclib.pojo.transaction;

import java.math.BigInteger;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Confirm_unit_info {

    private String exec_status;
    private Long height;
    private String tx_exec_status;
    private String unit_hash;
    private BigInteger used_deposit;
    private Long used_disk;
    private Long used_gas;

    public String getExec_status() {
        return exec_status;
    }

    public void setExec_status(String exec_status) {
        this.exec_status = exec_status;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getTx_exec_status() {
        return tx_exec_status;
    }

    public void setTx_exec_status(String tx_exec_status) {
        this.tx_exec_status = tx_exec_status;
    }

    public String getUnit_hash() {
        return unit_hash;
    }

    public void setUnit_hash(String unit_hash) {
        this.unit_hash = unit_hash;
    }

    public BigInteger getUsed_deposit() {
        return used_deposit;
    }

    public void setUsed_deposit(BigInteger used_deposit) {
        this.used_deposit = used_deposit;
    }

    public Long getUsed_disk() {
        return used_disk;
    }

    public void setUsed_disk(Long used_disk) {
        this.used_disk = used_disk;
    }

    public Long getUsed_gas() {
        return used_gas;
    }

    public void setUsed_gas(Long used_gas) {
        this.used_gas = used_gas;
    }

    @Override
    public String toString() {
        return "Confirm_unit_info{" +
                "exec_status='" + exec_status + '\'' +
                ", height=" + height +
                ", tx_exec_status='" + tx_exec_status + '\'' +
                ", unit_hash='" + unit_hash + '\'' +
                ", used_deposit=" + used_deposit +
                ", used_disk=" + used_disk +
                ", used_gas=" + used_gas +
                '}';
    }
}