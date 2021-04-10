package org.topnetwork.grpclib.pojo.election;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ElectionGrpcResult {

    private String result;
    private Value value;
    public void setResult(String result) {
         this.result = result;
     }
     public String getResult() {
         return result;
     }

    public void setValue(Value value) {
         this.value = value;
     }
     public Value getValue() {
         return value;
     }

}