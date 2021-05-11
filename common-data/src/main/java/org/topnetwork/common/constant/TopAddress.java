package org.topnetwork.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CasonCai
 * @since 2021/5/6 2:25 下午
 **/
public class TopAddress {

    /******************************** 查询选举块的账号 **********************************************/
    //用来查询auditor、validator选举节点信息的账号
    public static String ELECTION_AUDITOR_ADDRESS = "T200024uHxGKRST3hk5tKFjVpuQbGNDihMJR6qeeQ@2";
    public static String ELECTION_VALIDATOR_ADDRESS = "T200024uHxGKRST3hk5tKFjVpuQbGNDihMJR6qeeQ@2";
    //查询edge节点选举块
    public static String ELECTION_EDGE_ADDRESS = "T2000138NpRxYCFQxMHvedTxRpgkb8B7oHt235N2W@0";
    //archive节点选举块
    public static String ELECTION_ARCHIVE_ADDRESS = "T2000138NXb36GkofBUMqxCAZqdERi63htDVC8Yzt@0";
    //rootbeacon节点选举块
    public static String ELECTION_ROOTBEACON_ADDRESS = "T2000138JQPo5TcurZsVLFUMd5vHJRBLenLWjLhk6@0";
    //subbeacon节点选举块
    public static String ELECTION_SUBBEACON_ADDRESS = "T2000138Kc9WynduqxJvX3VCU7XjHCR9YyKuBL1fx@0";

    /******************************** 查询tableblock的地址 **********************************************/
    public static String BASE_TABLEBLOCK_ADDRESS = "Ta0000gRD2qVpp2S7UpjAsznRiRhbE1qNnhMbEDp@";

    //tableblock地址
    public static List<String> TABLEBLOCK_ADDRESSES = new ArrayList<>();

    static {
        TABLEBLOCK_ADDRESSES = buildTableBlockAddresses();
    }


    private static List<String> buildTableBlockAddresses() {
        List<String> tableBlockAddresses = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            tableBlockAddresses.add(BASE_TABLEBLOCK_ADDRESS + i);
        }
        return tableBlockAddresses;
    }
}
