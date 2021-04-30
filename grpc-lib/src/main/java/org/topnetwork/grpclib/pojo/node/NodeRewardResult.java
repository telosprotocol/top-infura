package org.topnetwork.grpclib.pojo.node;

import java.util.HashMap;

public class NodeRewardResult {


    private HashMap<String, NodeRewardValue>  value;


    public void setValue(HashMap<String, NodeRewardValue> value) {
         this.value = value;
     }
     public HashMap<String, NodeRewardValue> getValue() {
         return value;
     }

    @Override
    public String toString() {
        return "QueryNodeRewardResult{" +
                "value=" + value +
                '}';
    }
}