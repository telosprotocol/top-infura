package org.topnetwork.analysis.mq.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author CasonCai
 * @since 2021/4/22 下午2:28
 **/
@Data
@AllArgsConstructor
public class NewTransactionMessage {

    private String txHash;

    private String from;

    private String to;

    private BigInteger amount;

}
