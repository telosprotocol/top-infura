package org.topnetwork.grpclib.xrpc;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.topnetwork.grpclib.pojo.election.ElectionBlockResult;
import org.topnetwork.grpclib.pojo.node.IssuanceDetailResult;
import org.topnetwork.grpclib.pojo.node.NodeResult;
import org.topnetwork.grpclib.pojo.node.NodeRewardResult;
import org.topnetwork.grpclib.pojo.stream.LightUnitInput;
import org.topnetwork.grpclib.pojo.stream.TableBlockResult;
import org.topnetwork.grpclib.pojo.stream.Tableblock;
import org.topnetwork.grpclib.pojo.stream.UnitsBlockMap;
import org.topnetwork.grpclib.pojo.transaction.TransactionResult;
import org.topnetwork.grpclib.pojo.unit.Lightunit;
import org.topnetwork.grpclib.pojo.unit.UnitBlockResult;
import org.topnetwork.grpclib.pojo.unit.UnitBlockValue;
import org.topnetwork.rpclib.TopRpcClient;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TopGrpcClientTest {

    TopGrpcClient topGrpcClient;

    TopRpcClient topRpcClient;
    @Before
    public void setUp() {
        topGrpcClient = TopGrpcClient.getInstance("206.189.190.31", 19082);
        topRpcClient = new TopRpcClient("206.189.190.31", 19081);
    }

    @Test
    public void testGetNodeInfo() {
        NodeResult nodeResult = topGrpcClient.getNodeInfo();
    }


    @Test
    public void testStream() {
        Iterator<TableBlockResult> tableBlocks = topGrpcClient.getTableBlockStream();
        while (tableBlocks.hasNext()) {
            TableBlockResult resp = tableBlocks.next();
            String body = JSON.toJSONString(resp);

            System.out.println("tableBlock:" + body);
            Tableblock tableblock = resp.getValue().getBody().getTableblock();
            if (tableblock == null) {
                continue;
            }
            Map<String, UnitsBlockMap> unitsBlockMapMap = resp.getValue().getBody().getTableblock().getUnits();
            for (Map.Entry<String, UnitsBlockMap> entry : unitsBlockMapMap.entrySet()) {
                String account = entry.getKey();
                UnitsBlockMap unitsBlockMap = entry.getValue();
                Long unitHeight = unitsBlockMap.getUnit_height();
                Map<String, LightUnitInput> lightUnitInputMap = unitsBlockMap.getLightunit_input();
                if (lightUnitInputMap == null) {
                    continue;
                }

                UnitBlockResult unitBlockResult = getUnitBlock(account, (long) unitHeight);
                String unitBlockJson = JSON.toJSONString(unitBlockResult);
                System.out.println("unitBlock:" + unitBlockJson);
            }
        }
    }

    public UnitBlockResult getUnitBlock(String address, Long height) {
        return topGrpcClient.getUnitBlock(address, height);
    }


    @Test
    public void testGetUnitBlock() {
        String address = "";
        Long height = 1L;
        UnitBlockResult unitBlockResult = topGrpcClient.getUnitBlock(address, height);
        unitBlockResult.getValue().getBalance();
        unitBlockResult.getValue().getBody().getLightunit();
    }

    @Test
    public void testTransaction() {
        TransactionResult result = topGrpcClient.getTransaction("0x5c4e96fad0c6e54bfeb225893542f0f02df23a23a980572b2f54c0881c9bd36e");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetTableBlock() {

        topGrpcClient.getUnitBlock("Ta00025E3y1TBsbnvZHcq1eELBBQH4D5tFbg1jQqY@2", 80312L);

    }

    @Test
    public void testGetElection(){
        ElectionBlockResult result = topGrpcClient.getElectionBlock("T200024uHxGKRST3hk5tKFjVpuQbGNDihMJR6qeeQ@2", 10L);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetReward() {
        String address = "T200024uV5yB1ZCnXe7SbViA86ufhouFjpDKNRd3X@0";
        Long height = 598L;
        UnitBlockResult unitBlockResult = topGrpcClient.getUnitBlock(address, height);
        if (haveStatReward(unitBlockResult.getValue())) {
            IssuanceDetailResult result = topGrpcClient.getIssuanceDetail(height);
            System.out.println(JSON.toJSONString(result));
        }

    }


    /**
     * 是否有统计块
     *
     * @param unitBlock
     * @return
     */
    public boolean haveStatReward(UnitBlockValue unitBlock) {
        Lightunit lightunit = unitBlock.getBody().getLightunit();
        if (lightunit == null) {
            return false;
        }

        Object cumstomPropertyKey = lightunit.getLightunit_state().getCustom_property_key();
        if (cumstomPropertyKey instanceof List) {
            return ((List<?>) cumstomPropertyKey).contains("#146");
        }

        return false;
    }

    @Test
    public void testGetAllNode(){
        NodeResult nodeResult = topGrpcClient.getNodeInfo();
        System.out.println(JSON.toJSONString(nodeResult));

        NodeRewardResult rewardResult = topGrpcClient.queryNodeReward();
        System.out.println(rewardResult);
    }

    @Test
    public void testListVoteUsed(){
        String result = topRpcClient.listVoteUsed("T00000LaZkh8p5hFGsHsWz6JbAKqnAeVjtSviq4p");
        System.out.println(result);

    }
}