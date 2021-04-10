package org.topnetwork.grpclib.pojo.stream;

import java.util.HashMap;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class UnitsBlockMap {

    private HashMap<String, LightUnitInput> lightunit_input;
    private LightUnitState lightunitState;
    private int unit_height;

    public HashMap<String, LightUnitInput> getLightunit_input() {
        return lightunit_input;
    }

    public void setLightunit_input(HashMap<String, LightUnitInput> lightunit_input) {
        this.lightunit_input = lightunit_input;
    }

    public LightUnitState getLightunitState() {
        return lightunitState;
    }

    public void setLightunitState(LightUnitState lightunitState) {
        this.lightunitState = lightunitState;
    }

    public int getUnit_height() {
        return unit_height;
    }

    public void setUnit_height(int unit_height) {
        this.unit_height = unit_height;
    }

    @Override
    public String toString() {
        return "UnitsBlockMap{" +
                "lightunit_input=" + lightunit_input +
                ", lightunitState=" + lightunitState +
                ", unit_height=" + unit_height +
                '}';
    }
}