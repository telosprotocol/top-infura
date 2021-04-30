package org.topnetwork.analysis.mq.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author CasonCai
 * @since 2021/4/22 下午2:20
 **/
@Data
@AllArgsConstructor
public class NewUnitBlockMessage {
    private String hash;

    private String owner;

    private Long height;


}
