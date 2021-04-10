package org.topnetwork.grpclib.pojo.transaction;

import java.math.BigInteger;

public class UnitInfo {
    private BigInteger height;
    private BigInteger tx_fee;
    private String unit_hash;
    private BigInteger used_deposit;
    private BigInteger used_disk;
    private BigInteger used_gas;

    public BigInteger getHeight() {
        return height;
    }

    public void setHeight(BigInteger height) {
        this.height = height;
    }
    
    public BigInteger getTx_fee() {
		return tx_fee;
	}

	public void setTx_fee(BigInteger tx_fee) {
		this.tx_fee = tx_fee;
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

    public BigInteger getUsed_disk() {
        return used_disk;
    }

    public void setUsed_disk(BigInteger used_disk) {
        this.used_disk = used_disk;
    }

    public BigInteger getUsed_gas() {
        return used_gas;
    }

    public void setUsed_gas(BigInteger used_gas) {
        this.used_gas = used_gas;
    }

    @Override
    public String toString() {
        return "UnitInfo{" +
                "height=" + height +
                ", unit_hash='" + unit_hash + '\'' +
                ", used_deposit=" + used_deposit +
                ", used_disk=" + used_disk +
                ", used_gas=" + used_gas +
                '}';
    }
}
