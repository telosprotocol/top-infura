package org.topnetwork.grpclib.pojo.node;

import java.util.HashMap;

public class NodeResult {
    private Object result;
    private HashMap<String, NodeInfoResult> value;

    public HashMap<String, NodeInfoResult> getValue() {
        return value;
    }

    public void setValue(HashMap<String, NodeInfoResult> value) {
        this.value = value;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "NodeResult{" +
                "result=" + result +
                ", value=" + value +
                '}';
    }
}
