package org.topnetwork.grpclib.pojo.node;

import java.util.List;
import java.util.Map;

public class OnlineListResult {
	
    private Map<String,List<ElectionNodeValue>> value;


    public Map<String,List<ElectionNodeValue>> getValue() {
        return value;
    }

    public void setValue(Map<String,List<ElectionNodeValue>> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "OnlineListResult{" +
                "value=" + value +
                '}';
    }
}
