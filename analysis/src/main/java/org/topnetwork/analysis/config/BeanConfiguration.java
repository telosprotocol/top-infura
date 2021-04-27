package org.topnetwork.analysis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

/**
 * @author CasonCai
 * @since 2021/4/21 上午9:19
 **/
@Configuration
public class BeanConfiguration {

    @Value("${top.grpc.ip}")
    String grpcIp;

    @Value("${top.grpc.port}")
    int grpcPort;

    @Bean
    public TopGrpcClient grpcClient(){
        TopGrpcClient grpcClient = TopGrpcClient.getInstance(grpcIp, grpcPort);
        return grpcClient;
    }


}
