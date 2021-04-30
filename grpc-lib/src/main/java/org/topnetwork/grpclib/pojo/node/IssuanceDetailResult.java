package org.topnetwork.grpclib.pojo.node;

import lombok.Data;
import org.topnetwork.grpclib.pojo.TopGrpcResponse;

import java.util.Map;

/**
 * @author CasonCai
 * @since 2021/4/27 上午11:05
 **/
@Data
public class IssuanceDetailResult extends TopGrpcResponse<Map<String, IssuanceDetailValue>> {

}
