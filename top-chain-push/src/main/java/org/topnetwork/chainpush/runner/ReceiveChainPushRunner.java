package org.topnetwork.chainpush.runner;

import org.apache.rocketmq.client.producer.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.topnetwork.chainpush.mq.BlockStreamProducer;
import org.topnetwork.grpclib.enums.TopMethod;
import org.topnetwork.grpclib.pojo.stream.TableBlockResult;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;
import org.topnetwork.grpclib.xrpc.xrpc_reply;
import org.topnetwork.grpclib.xrpc.xrpc_serviceGrpc;

import java.util.Iterator;

public class ReceiveChainPushRunner implements ApplicationRunner {

    @Value("${top.grpc.ip}")
    private String ip;
    @Value("${top.grpc.port}")
    private int port;
    @Value("${mq.topic.tableblock}")
    private String topic;

    private static final Logger LOG = LoggerFactory.getLogger(ReceiveChainPushRunner.class);
    private static xrpc_serviceGrpc.xrpc_serviceBlockingStub blockingStub = null;
    @Autowired
    private BlockStreamProducer blockStreamProducer;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    public void run_imp() {
        try {
            LOG.info("Stream block start ip:{},port:{}", ip, port);
            Iterator<xrpc_reply> it = TopGrpcClient.getInstance(ip, port).createRequest(TopMethod.GET_BLOCK_STREAM.getMethodName(), null).invokeStream();
            while (it.hasNext()) {
                xrpc_reply xrpc_reply = it.next();
                SendResult sendResult=new SendResult();
                SendResult wsSendResult=new SendResult();
                try {
                    //解析数据
                    TableBlockResult tableBlockResult = TopGrpcClient.xrpc_reply2Stream(xrpc_reply);
                    LOG.info("recv data:", tableBlockResult.toString());
                    sendResult= blockStreamProducer.syncSend(topic, tableBlockResult);
                } catch (Exception e) {
                    LOG.info("mq异常" + e.getMessage());
                } finally {
                    LOG.info(xrpc_reply.getBody()+"===send Result=="+sendResult);
                }
            }
        } catch (Exception e) {
            LOG.info("TOPGRPCClient error （ip:=" + ip + " port=" + port + ")  message{}", e.getMessage());
            try {
                Thread.sleep(10000);
//                TopGrpcClient.getInstance(ip, port).reconnect(ip, port);
            } catch (InterruptedException e1) {
            }
            LOG.info("TOPGRPCClient error （ip:=" + ip + " port=" + port + ")  重新连接");
        }
    }
}
