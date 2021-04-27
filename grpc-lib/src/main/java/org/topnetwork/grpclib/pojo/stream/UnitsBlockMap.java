package org.topnetwork.grpclib.pojo.stream;

import lombok.Data;

import java.util.HashMap;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class UnitsBlockMap {

    private HashMap<String, LightUnitInput> lightunit_input;
    private LightUnitState lightunitState;
    private Long unit_height;


    @Override
    public String toString() {
        return "UnitsBlockMap{" +
                "lightunit_input=" + lightunit_input +
                ", lightunitState=" + lightunitState +
                ", unit_height=" + unit_height +
                '}';
    }
}