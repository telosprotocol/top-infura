package org.topnetwork.grpclib.enums;

/**
 * @description: 六种特殊选举账号，实为5种
 **/
public enum AddressEnum {
    /**
     * edge
     */
    edge("T2000138NpRxYCFQxMHvedTxRpgkb8B7oHt235N2W@0"),
    /**
     * archive
     */
    archive("T2000138NXb36GkofBUMqxCAZqdERi63htDVC8Yzt@0"),
    /**
     * root_beacon
     */
    root_beacon("T2000138JQPo5TcurZsVLFUMd5vHJRBLenLWjLhk6@0"),
    /**
     * sub_beacon
     */
    sub_beacon("T2000138Kc9WynduqxJvX3VCU7XjHCR9YyKuBL1fx@0"),
    /**
     * auditor
     */
    auditor("T200024uHxGKRST3hk5tKFjVpuQbGNDihMJR6qeeQ@2"),
    /**
     * validator
     */
    validator("T200024uHxGKRST3hk5tKFjVpuQbGNDihMJR6qeeQ@2");

    private String address;

    AddressEnum(String address) {
        this.address=address;
    }

    public String getAddress() {
        return address;
    }

}
