package org.topnetwork.grpclib.pojo.unit;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class UnitBlockResult {

//    private String result;
    private UnitBlockValue value;


    public void setValue(UnitBlockValue value) {
         this.value = value;
     }
     public UnitBlockValue getValue() {
         return value;
     }

}