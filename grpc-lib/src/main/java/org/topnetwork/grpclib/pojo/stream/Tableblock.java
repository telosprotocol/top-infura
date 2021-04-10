package org.topnetwork.grpclib.pojo.stream;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Tableblock implements Serializable {

    private HashMap<String,UnitsBlockMap> units;

    public HashMap<String, UnitsBlockMap> getUnits() {
        return units;
    }

    public void setUnits(HashMap<String, UnitsBlockMap> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Tableblock{" +
                "units=" + units +
                '}';
    }
}