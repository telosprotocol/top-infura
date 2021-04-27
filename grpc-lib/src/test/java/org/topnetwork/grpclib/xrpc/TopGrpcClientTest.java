package org.topnetwork.grpclib.xrpc;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.topnetwork.grpclib.pojo.node.NodeResult;
import org.topnetwork.grpclib.pojo.stream.LightUnitInput;
import org.topnetwork.grpclib.pojo.stream.TableBlockResult;
import org.topnetwork.grpclib.pojo.stream.Tableblock;
import org.topnetwork.grpclib.pojo.stream.UnitsBlockMap;
import org.topnetwork.grpclib.pojo.transaction.TransactionResult;
import org.topnetwork.grpclib.pojo.unit.UnitBlockResult;

import java.util.Iterator;
import java.util.Map;

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
        Iterator<TableBlockResult> tableBlocks = topGrpcClient.getTableBlockStream();
        while(tableBlocks.hasNext()){
            TableBlockResult resp = tableBlocks.next();
            String body = JSON.toJSONString(resp);

            System.out.println("tableBlock:" + body);
            Tableblock tableblock = resp.getValue().getBody().getTableblock();
            if(tableblock == null){
                continue;
            }
            Map<String, UnitsBlockMap> unitsBlockMapMap = resp.getValue().getBody().getTableblock().getUnits();
            for (Map.Entry<String, UnitsBlockMap> entry : unitsBlockMapMap.entrySet()) {
                String account = entry.getKey();
                UnitsBlockMap unitsBlockMap = entry.getValue();
                Long unitHeight = unitsBlockMap.getUnit_height();
                Map<String, LightUnitInput> lightUnitInputMap = unitsBlockMap.getLightunit_input();
                if(ObjectUtils.isEmpty(lightUnitInputMap)){
                    continue;
                }

                UnitBlockResult unitBlockResult = getUnitBlock(account,(long) unitHeight);
                String unitBlockJson = JSON.toJSONString(unitBlockResult);
                System.out.println("unitBlock:" + unitBlockJson);
            }
        }
    }

    public UnitBlockResult getUnitBlock(String address, Long height){
        return topGrpcClient.getUnitBlock(address, height);
    }


    @Test
    public void testGetUnitBlock(){
        String address = "";
        Long height = 1L;
        UnitBlockResult unitBlockResult = topGrpcClient.getUnitBlock(address, height);
        unitBlockResult.getValue().getBalance();
        unitBlockResult.getValue().getBody().getLightunit();
    }

    @Test
    public void testTransaction(){
        TransactionResult result = topGrpcClient.getTransaction("0x7a2e0278aee15c5721ca5354d6f4498c80477d999c6dc663b65f59587079830a");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetTableBlock(){

        topGrpcClient.getUnitBlock("Ta00025E3y1TBsbnvZHcq1eELBBQH4D5tFbg1jQqY@2", 80312L);

    }

}