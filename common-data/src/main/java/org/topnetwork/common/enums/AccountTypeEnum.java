package org.topnetwork.common.enums;

import org.springframework.util.ObjectUtils;

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

    /**
     * 根据地址验证地址类型
     *
     * @param address
     * @return
     */
    public static AccountTypeEnum ofAddress(String address) {

        if (ObjectUtils.isEmpty(address)) {
            return AccountTypeEnum.OTHER;
        }
        AccountTypeEnum type = AccountTypeEnum.OTHER;
        if (address.startsWith("T00000")) {
            type = AccountTypeEnum.ACCOUNT_PREFIX;
        }
        if (address.startsWith("T10000")) {
            type = AccountTypeEnum.SUB_ACCOUNT_PREFIX;
        }
        if (address.startsWith("T20001")) {
            type = AccountTypeEnum.SYS_BEACON_CONTRACT_PREFIX;
        }
        if (address.startsWith("T20002")) {
            type = AccountTypeEnum.SYS_ZEC_CONTRACT_PREFIX;
        }
        if (address.startsWith("T20000")) {
            type = AccountTypeEnum.SYS_SHARDING_CONTRACT_PREFIX;
        }
        if (address.startsWith("T30000")) {
            type = AccountTypeEnum.USER_CONTRACT_PREFIX;
        }
        return type;
    }

}
