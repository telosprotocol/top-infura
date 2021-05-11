package org.topnetwork.grpclib.pojo.unit;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Lightunit_state {

    private BigInteger balance_change;
    private BigInteger burned_amount_change;
    private Object custom_property_key;
    private Object native_property;
}