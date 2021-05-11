package org.topnetwork.api.bean.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author CasonCai
 * @since 2021/5/10 9:38 上午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeRewardDetailResp {

    private Long timestamp;

    private BigInteger reward;

}
