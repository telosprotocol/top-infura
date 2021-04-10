package org.topnetwork.grpclib.pojo.node;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class StandbysResult {


    private StandbyValue value;


    public void setValue(StandbyValue value) {
         this.value = value;
     }
     public StandbyValue getValue() {
         return value;
     }

    @Override
    public String toString() {
        return "StandbysResult{" +
                "value=" + value +
                '}';
    }
}