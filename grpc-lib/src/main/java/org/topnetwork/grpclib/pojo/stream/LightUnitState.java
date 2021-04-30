package org.topnetwork.grpclib.pojo.stream;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class LightUnitState implements Serializable {

    private BigInteger balance_change;
    private BigInteger burned_amount_change;
    private String custom_property_key;
    private List<String> native_property;

    @Override
    public String toString() {
        return "LightUnitState{" +
                "balance_change=" + balance_change +
                ", burned_amount_change=" + burned_amount_change +
                ", custom_property_key='" + custom_property_key + '\'' +
                ", native_property=" + native_property +
                '}';
    }
}