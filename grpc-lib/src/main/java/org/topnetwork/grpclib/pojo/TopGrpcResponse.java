package org.topnetwork.grpclib.pojo;

import lombok.Data;

/**
 * @author CasonCai
 * @since 2021/4/27 下午2:36
 **/
@Data
public class TopGrpcResponse<T> {
    /**
     * 返回结果
     */
    Object result;

    /**
     * 返回内容
     * 有些接口是value，有些是data，看情况取结果
     */
    T value;

    /**
     * 返回内容，同value
     */
    T data;

}
