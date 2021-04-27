package org.topnetwork.analysis.utils;

import org.springframework.util.ObjectUtils;
import org.topnetwork.common.enums.ChainZoneType;

public class ZoneRuleUtils {

    public final static int VACCOUNT_ADDRESS_MIN_SIZE = 19;//(>20,<256)

    public final static int VACCOUNT_ADDRESS_MAX_SIZE = 256;

    /**
     * 通过地址获取ChainZoneType
     * @param accountAddr
     * @return
     */
    public static ChainZoneType getChainZoneType(String accountAddr) {
        return ChainZoneType.getChainZoneType(get_zoneindex_from_ledgerid(getLedgerId(accountAddr)));
    }

    public static int getLedgerId(String accountAddr) {
        if (ObjectUtils.isEmpty(accountAddr)){
            return 0;
        }
        int ledgerId = 0;
        int accountAddrSize = accountAddr.length();
        if(accountAddrSize>VACCOUNT_ADDRESS_MIN_SIZE && accountAddrSize < VACCOUNT_ADDRESS_MAX_SIZE) {
            //从第三个开始查找字符‘-’第一次出现的位置
            String ledgerIdStr = accountAddr.substring(2,6);
            ledgerId = Integer.valueOf(ledgerIdStr);
        }
        return ledgerId;
    }
    //取得zone id
    public static short get_zoneindex_from_ledgerid(int ledgerId) {
        return (short)(ledgerId & 0xf);
    }

    public static void main(String[] args) {
        System.out.println(getLedgerId("T200024uAt8Na2U1GUtWSHXJSqaJJBXunUX9WU9kB"));
    }
}
