package org.topnetwork.grpclib.pojo.node;

import java.util.HashMap;

public class QueryNodeRewardResult {


    private HashMap<String, QueryNodeRewardValue>  value;


    public void setValue(HashMap<String, QueryNodeRewardValue> value) {
         this.value = value;
     }
     public HashMap<String, QueryNodeRewardValue> getValue() {
         return value;
     }

    @Override
    public String toString() {
        return "QueryNodeRewardResult{" +
                "value=" + value +
                '}';
    }
}