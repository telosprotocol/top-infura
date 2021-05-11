package org.topnetwork.grpclib.pojo.transaction;

import lombok.Data;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class TransactionResult {
    private String result;
    private Value value;


    @Override
    public String toString() {
        return "TransactionResult{" +
                "result='" + result + '\'' +
                ", value=" + value +
                '}';
    }
}