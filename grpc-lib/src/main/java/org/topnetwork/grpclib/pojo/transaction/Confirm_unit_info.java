package org.topnetwork.grpclib.pojo.transaction;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Confirm_unit_info {

    private String exec_status;
    private Long height;
    private String tx_exec_status;
    private String unit_hash;
    private BigInteger used_deposit;
    private BigInteger used_disk;
    private BigInteger used_gas;


}