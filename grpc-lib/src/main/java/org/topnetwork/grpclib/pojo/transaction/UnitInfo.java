package org.topnetwork.grpclib.pojo.transaction;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UnitInfo {
    private Long height;
    private BigInteger tx_fee;
    private String unit_hash;
    private BigInteger used_deposit;
    private BigInteger used_disk;
    private BigInteger used_gas;

}
