package org.topnetwork.grpclib.pojo.transaction;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TransactionResult {
    private String result;
    private Value value;

    public void setValue(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TransactionResult{" +
                "result='" + result + '\'' +
                ", value=" + value +
                '}';
    }
}