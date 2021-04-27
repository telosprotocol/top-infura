package org.topnetwork.analysis.mq.message;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author CasonCai
 * @since 2021/4/22 下午2:30
 **/
@Data
@AllArgsConstructor
public class NewAccountMessage {

    private String address;

}
