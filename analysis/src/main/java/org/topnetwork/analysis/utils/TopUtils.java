package org.topnetwork.analysis.utils;

import org.springframework.util.ObjectUtils;
import org.topnetwork.grpclib.enums.AccountTypeEnum;

public class TopUtils {
    /**
     * 根据地址验证地址类型
     * @param accountAddress
     * @return
     */
    public static AccountTypeEnum getAccountType(String accountAddress) {
        if (ObjectUtils.isEmpty(accountAddress)){
            return AccountTypeEnum.OTHER;
        }
        AccountTypeEnum type = AccountTypeEnum.OTHER;
        if (accountAddress.startsWith("T00000")) {
            type = AccountTypeEnum.ACCOUNT_PREFIX;
        }
        if (accountAddress.startsWith("T10000")) {
            type = AccountTypeEnum.SUB_ACCOUNT_PREFIX;
        }
        if (accountAddress.startsWith("T20001")) {
            type = AccountTypeEnum.SYS_BEACON_CONTRACT_PREFIX;
        }
        if (accountAddress.startsWith("T20002")) {
            type = AccountTypeEnum.SYS_ZEC_CONTRACT_PREFIX;
        }
        if (accountAddress.startsWith("T20000")) {
            type = AccountTypeEnum.SYS_SHARDING_CONTRACT_PREFIX;
        }
        if (accountAddress.startsWith("T30000")) {
            type = AccountTypeEnum.USER_CONTRACT_PREFIX;
        }
        return type;
    }
}
