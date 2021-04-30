package org.topnetwork.grpclib.xrpc;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import org.springframework.util.ObjectUtils;

import org.topnetwork.grpclib.pojo.account.AccountResult;
import org.topnetwork.grpclib.pojo.election.ElectionBlockResult;
import org.topnetwork.grpclib.pojo.node.*;
import org.topnetwork.grpclib.pojo.shard.GeneralInfos;
import org.topnetwork.grpclib.pojo.stream.TableBlockResult;
import org.topnetwork.grpclib.pojo.timer.TimerValue;
import org.topnetwork.grpclib.pojo.transaction.TransactionResult;
import org.topnetwork.grpclib.pojo.unit.UnitBlockResult;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TopGrpcClient {
    private static volatile TopGrpcClient client = new TopGrpcClient();
    private static xrpc_serviceGrpc.xrpc_serviceBlockingStub blockingStub = null;

    private final String getArcs = "getArcs";
    private final String getEdges = "getEdges";
    private final String getZecs = "getZecs";
    private final String getRecs = "getRecs";
    private final int keepalivetime = 60; // keepalivetime 60 second
    private final int keepalivetimeout = 3; // keepalivetime 3 minutes
    private final int deadline = 30; // deadline 30 minutes

    private TopGrpcClient() {
    }

    //单例模式初始化TOPGRPCClient和blockingStub
    public static TopGrpcClient getInstance(String ip, int port) {
        return client.init(ip, port);
    }

    private TopGrpcClient init(String ip, int port) {
        if (blockingStub == null) {
            synchronized (TopGrpcClient.class) {
                if (blockingStub == null) {
                    try {
                        ManagedChannel channel = ManagedChannelBuilder.forAddress(ip, port).keepAliveTime(keepalivetime, TimeUnit.SECONDS).keepAliveTimeout(keepalivetimeout, TimeUnit.MINUTES).keepAliveWithoutCalls(true).usePlaintext().build();
                        blockingStub = xrpc_serviceGrpc.newBlockingStub(channel);
                    } catch (Exception e) {
                        throw new RuntimeException("top grpc client create error:" + e.getMessage());
                    }
                }
            }
        }
        return this;
    }

    public xrpc_request createRequest(String action, Map<String, Object> params) {
        if (params == null || params.size() == 0) {
            params = new HashMap<>();
        }
        params.put("action", action);
        String str = JSON.toJSONString(params);
        xrpc_request request = xrpc_request.newBuilder().setAction(action).setBody(str).build();
        return request;
    }

    public String callRequest(xrpc_request request) {
        try {
            xrpc_reply xrpc_reply = blockingStub.call(request);
            return xrpc_reply.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取tableblock流，监听最新的tableblcok
     * @return
     */
    public Iterator<TableBlockResult> getTableBlockStream(){
        xrpc_request request = createRequest("stream_table",null);
        Iterator<xrpc_reply> streamResp = getStreamResp(request);
        return new TableBlockIterator(streamResp);
    }

    public Iterator<xrpc_reply> getStreamResp(xrpc_request request) {
        try {
            Iterator<xrpc_reply> it = blockingStub.tableStream(request);
            return it;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    private <T> T getBlock(String address, Long height, Class<T> clazz){
        T blockResp = null;
        HashMap<String, Object> map = new HashMap<>();
        map.put("account_addr", address);
        map.put("type", "height");
        map.put("height", height);

        xrpc_request request = this.createRequest("getBlock",map);
        String getBlock = callRequest(request);
        blockResp = JSON.parseObject(getBlock, clazz);
        return blockResp;
    }

    private <T> T getLatestBlock(String address, Class<T> clazz){
        T blockResp = null;
        HashMap<String, Object> map = new HashMap<>();
        map.put("account_addr", address);
        map.put("type", "last");
        xrpc_request request = this.createRequest("getBlock", map);
        String getBlock = callRequest(request);
        blockResp = JSON.parseObject(getBlock, clazz);
        return blockResp;
    }

    /**
     * 最新的tableblock
     * @param address
     * @return
     */
    public TableBlockResult getLatestTableBlock(String address){
        return getLatestBlock(address, TableBlockResult.class);
    }

    /**
     *
     * @param address
     * @param height
     * @return
     */
    public TableBlockResult getTableBlock(String address, Long height) {
        return getBlock(address, height, TableBlockResult.class);
    }

    public UnitBlockResult getLatestUnitBlock(String address){
        return getLatestBlock(address, UnitBlockResult.class);
    }

    public UnitBlockResult getUnitBlock(String address, Long height) {
        return getBlock(address, height, UnitBlockResult.class);
    }

    /**
     * 查询ElectionBlock(选举节点的区块)
     * @param address
     * @param height
     * @return
     */
    public ElectionBlockResult getElectionBlock(String address, Long height){
        return getBlock(address, height, ElectionBlockResult.class);
    }

    /**
     * 查询ElectionBlock(选举节点的区块)
     * @param address
     * @return
     */
    public ElectionBlockResult getLatestElectionBlock(String address){
        return getLatestBlock(address, ElectionBlockResult.class);
    }

    static class TimerQueryRunner implements  Runnable {
        private TopGrpcClient m_ins;
        public TimerQueryRunner(TopGrpcClient instance) {
            m_ins = instance;
        }
        @Override
        public void run() {
            while (true) {
                run_imp();
            }
        }

        void run_imp() {
            try {
                 TimerValue tv = m_ins.getTimerInfo();
                 System.out.printf("time %s\n", tv.getTimer_clock().toString());
                 Thread.sleep(3000);
            } catch (Exception e) {
                System.out.printf("timer exception %s", e.getMessage());
            }
        }
    }



    static class Subscriber implements Runnable {
        private TopGrpcClient m_ins;
        private Long m_round;
        public Subscriber(TopGrpcClient instance) {
            m_ins = instance;
        }
        @Override
        public void run() {
            while (true) {
                run_imp();
            }
        }

        void run_imp() {
            try {
                xrpc_request request = m_ins.createRequest("stream_table", null);
                Iterator<xrpc_reply> it =m_ins.getStreamResp(request);

                while (it.hasNext()) {
                    xrpc_reply xrpc_reply = it.next();
                    SimpleDateFormat f = new SimpleDateFormat("yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
                    System.out.printf("[%s] recv data : %s\n", f.format(new Date()).toString(), xrpc_reply.toString());
                }

                m_round++;
                if (m_round % 100 == 0) {
                    Thread.sleep(60 * 1000);
                }
            } catch (Exception e) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
                System.out.printf("[%s] grpc error : %s\n", f.format(new Date()).toString(), e.toString());
                try {
                    Thread.sleep(1000);
//                    m_ins.reconnect("206.189.193.111", 19082);
                } catch (InterruptedException e1) {
                }
            }
        }
    }


    public TransactionResult getTransaction(String hash) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tx_hash", hash);

        xrpc_request request = this.createRequest("getTransaction",map);
        String tx = callRequest(request);

        TransactionResult accountResult = JSON.parseObject(tx, TransactionResult.class);
        return accountResult;
    }

    public AccountResult getAccount(String address) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("account_addr", address);

        xrpc_request request = this.createRequest("getAccount",map);
        String account = callRequest(request);

        AccountResult accountResult = JSON.parseObject(account, AccountResult.class);
        return accountResult;
    }

    /**
     * edges 当选节点信息
     * @return
     */
    public OnlineListResult getEdges() {
    	return getElectionNodeInfo(getEdges);
    }
    
    /**
     * arcs 当选节点信息
     * @return
     */
    public OnlineListResult getArcs() {
    	return getElectionNodeInfo(getArcs);
    }
    
    /**
     * zecs 当选节点信息
     * @return
     */
    public OnlineListResult getZecs() {
        return getElectionNodeInfo(getZecs);
    }
    
    /**
     * recs 当选节点信息
     * @return
     */
    public OnlineListResult getRecs() {
        return getElectionNodeInfo(getRecs);
    }
    /**
     * 获取当选节点信息
     * @param action
     * @return
     */
    public OnlineListResult getElectionNodeInfo(String action) {
    	HashMap<String, Object> map = new HashMap<>();

        xrpc_request request = this.createRequest(action,map);
        String account = callRequest(request);

    	OnlineListResult onlineListResult = JSON.parseObject(account, OnlineListResult.class);
    	return onlineListResult;
    }
    /**
     * 获取注册节点详细信息列表
     * @return
     */
    public NodeResult getNodeInfo() {
        HashMap<String, Object> map = new HashMap<>();

        xrpc_request request = this.createRequest("queryNodeInfo",map);
        String nodeInfo = callRequest(request);
        NodeResult nodeResult = null;
        nodeResult = JSON.parseObject(nodeInfo, NodeResult.class);
        return nodeResult;
    }

    public ConsensusResult getConsensus() {
        HashMap<String, Object> map = new HashMap<>();

        xrpc_request request = this.createRequest("getConsensus",map);
        String account = callRequest(request);

        ConsensusClusterValue clusterValue = JSON.parseObject(account, ConsensusClusterValue.class);

        ConsensusResult consensusResult = new ConsensusResult();
        HashMap<String, List<Object>> auditorAll = new HashMap<>();
        HashMap<String, List<Object>> validatorAll = new HashMap<>();
        consensusResult.setAuditor(auditorAll);
        consensusResult.setValidator(validatorAll);

        if (!ObjectUtils.isEmpty(clusterValue)) {
            Map<String, Object> clusterMaps = clusterValue.getValue();
            for (String key : clusterMaps.keySet()) {
                if ("null".equalsIgnoreCase(clusterMaps.get(key).toString())) {
                    continue;
                }
                Map<String, Object> stringObjectMap = (HashMap)clusterMaps.get(key);
                for (String innerKey : stringObjectMap.keySet()) {
                	if(innerKey.equals("chain_id")) {//临时解决方案
                		continue;
                	}
                    HashMap nodeMap = (HashMap) stringObjectMap.get(innerKey);
                    if (innerKey.equalsIgnoreCase("auditor")) { ;
                        auditorAll.putAll(nodeMap);
                    }
                    if (innerKey.equalsIgnoreCase("validator")) {
                        validatorAll.putAll(nodeMap);
                    }
                }
            }
        }
        return consensusResult;
    }

    public StandbysResult getStandbys() {
        HashMap<String, Object> map = new HashMap<>();

        xrpc_request request = this.createRequest("getStandbys",map);
        String resultStr = callRequest(request);

        StandbysResult standbysResult = JSON.parseObject(resultStr, StandbysResult.class);
        return standbysResult;
    }
    
    public TimerValue getTimerInfo() {
        HashMap<String, Object> map = new HashMap<>();

        xrpc_request request = this.createRequest("getTimerInfo",map);
        String resultStr = callRequest(request);

        TimerValue tv = JSON.parseObject(resultStr, TimerValue.class);
        return tv;
    }
    
    public NodeRewardResult queryNodeReward() {
        HashMap<String, Object> map = new HashMap<>();

        xrpc_request request = this.createRequest("queryNodeReward",map);
        String account = callRequest(request);

        NodeRewardResult nodeRewardResult = JSON.parseObject(account, NodeRewardResult.class);
        return nodeRewardResult;
    }
    
    public GeneralInfos getGeneralInfos() {
        HashMap<String, Object> map = new HashMap<>();

        xrpc_request request = this.createRequest("getGeneralInfos",map);
        String infos = callRequest(request);
        GeneralInfos queryNodeRewardResult = JSON.parseObject(infos, GeneralInfos.class);
        return queryNodeRewardResult;
    }

    public IssuanceDetailResult getIssuanceDetail(Long height) {
        Map<String, Object> map = new HashMap<>();
        map.put("height", height);
        xrpc_request request = this.createRequest("getIssuanceDetail", map);
        String resp = callRequest(request);
        IssuanceDetailResult result = JSON.parseObject(resp, IssuanceDetailResult.class);
        return result;
    }



}
