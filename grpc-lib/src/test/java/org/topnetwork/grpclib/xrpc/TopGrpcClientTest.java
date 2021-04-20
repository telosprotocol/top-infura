package org.topnetwork.grpclib.xrpc;

import org.junit.Before;
import org.junit.Test;
import org.topnetwork.grpclib.pojo.node.NodeResult;

import java.util.Iterator;

public class TopGrpcClientTest {

    TopGrpcClient topGrpcClient;

    @Before
    public void setUp() {
        topGrpcClient = TopGrpcClient.getInstance("206.189.190.31", 19082);
    }

    @Test
    public void testGetNodeInfo(){
        NodeResult nodeResult = topGrpcClient.getNodeInfo();


    }

    @Test
    public void testStream(){
        Iterator<xrpc_reply> tableBlocks = topGrpcClient.getTableBlockStream();
        while(tableBlocks.hasNext()){
            xrpc_reply reply = tableBlocks.next();
            String result = reply.getResult();
            String body = reply.getBody();
            System.out.println(result);
            System.out.println(body);
        }
    }

}