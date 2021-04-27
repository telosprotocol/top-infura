package org.topnetwork.grpclib.pojo.stream;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class TableBlockResult implements Serializable {
    private String result;
    private Value value;

    @Override
    public String toString() {
        return "ReturnValue{" +
                "result" + result.toString() +
                "value=" + value +
                '}';
    }

}