package org.topnetwork.common.utils;

import org.springframework.util.ObjectUtils;
import org.topnetwork.common.constant.TopAddress;
import org.topnetwork.common.constant.TopConstants;
import org.topnetwork.common.enums.AccountTypeEnum;

import java.math.BigDecimal;
import java.math.BigInteger;

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


    public static BigDecimal realAmount(BigInteger chainAmount){
        return new BigDecimal(chainAmount).divide(BigDecimal.valueOf(1e6));
    }

    public static BigInteger chainAmount(BigDecimal realAmount){
        return realAmount.multiply(BigDecimal.valueOf(1e6)).toBigInteger();
    }


    public static Long convertTimerHeight2Timestamp(Long timerHeight){
        return TopConstants.FIRST_BLOCK_TIMESTAMP + timerHeight * 10;
    }

    public static boolean isTableBlockAddress(String address){
        return address.startsWith(TopAddress.BASE_TABLEBLOCK_ADDRESS);
    }

    public static Integer getTableBlockAddressNum(String address){
        if(!isTableBlockAddress(address)){
            return null;
        }

        String numPart = address.split("@")[1];
        return Integer.parseInt(numPart);
    }

}
