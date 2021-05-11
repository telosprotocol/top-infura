package org.topnetwork.common.constant;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author CasonCai
 * @since 2021/4/29 1:51 下午
 **/
public class TopConstants {

    //记录选举块高度的redis key
    public final static String ELECTION_BLOCK_HEIGHT_KEY = "ELECTION_BLOCK_HEIGHT";

    //记录tableblock最新高度的redis key
    public final static String TABLEBLCOK_LATEST_HEIGHT_KEY = "TABLEBLOCK_LATEST_HEIGHT";

    //top精度
    public final static BigDecimal TOP_ACCURACY = new BigDecimal(1e6);

    //创始块时间戳，时间高度转时间戳公式：时间高度*10+创始块时间戳=时间高度的时间戳
    public static final Long FIRST_BLOCK_TIMESTAMP = 1573189200L;

}
