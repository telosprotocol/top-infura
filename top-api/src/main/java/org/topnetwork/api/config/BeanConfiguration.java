package org.topnetwork.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

/**
 * @author CasonCai
 * @since 2021/4/30 10:45 上午
 **/
@Configuration
public class BeanConfiguration {

    @Value("${top.grpc.ip}")
    private String topGrpcIp;

    @Value("${top.grpc.port}")
    private Integer topGrpcPort;

    @Bean
    public TopGrpcClient topGrpcClient(){
        return TopGrpcClient.getInstance(topGrpcIp, topGrpcPort);
    }

}
