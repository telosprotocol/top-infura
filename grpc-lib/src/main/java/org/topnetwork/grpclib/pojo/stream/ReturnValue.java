package org.topnetwork.grpclib.pojo.stream;

import java.io.Serializable;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ReturnValue implements Serializable {
    private Long result;
    private Value value;


    public void setValue(Value value) {
         this.value = value;
     }
     public Value getValue() {
         return value;
     }

    @Override
    public String toString() {
        return "ReturnValue{" +
                "result" + result.toString() +
                "value=" + value +
                '}';
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}