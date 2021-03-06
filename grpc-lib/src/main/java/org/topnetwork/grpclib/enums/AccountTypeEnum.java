package org.topnetwork.grpclib.enums;

public enum AccountTypeEnum {
    /**
     * Description:"T-0-"  普通账户
     **/
    ACCOUNT_PREFIX,
    //  "T-1-"    子账户
    SUB_ACCOUNT_PREFIX,
    // "T-21-"   beacon合约
    SYS_BEACON_CONTRACT_PREFIX,
    //  "T-22-"    zec合约
    SYS_ZEC_CONTRACT_PREFIX,
    //"T-2-"    shard合约
    SYS_SHARDING_CONTRACT_PREFIX,
    //"T-3-"   用户合约
    USER_CONTRACT_PREFIX,
    //节点账户//提供给界面使用
    NODE,
    //其他
    OTHER;
}
