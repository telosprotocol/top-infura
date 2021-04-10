package org.topnetwork.grpclib.pojo.unit;
import java.util.Date;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Lightunit_state {

    private long balance_change;
    private int burned_amount_change;
    private Object custom_property_key;
    private Object native_property;
    public void setBalance_change(long balance_change) {
         this.balance_change = balance_change;
     }
     public long getBalance_change() {
         return balance_change;
     }

    public void setBurned_amount_change(int burned_amount_change) {
         this.burned_amount_change = burned_amount_change;
     }
     public int getBurned_amount_change() {
         return burned_amount_change;
     }

    public Object getCustom_property_key() {
        return custom_property_key;
    }

    public void setCustom_property_key(Object custom_property_key) {
        this.custom_property_key = custom_property_key;
    }

    public void setNative_property(Object native_property) {
         this.native_property = native_property;
     }
     public Object getNative_property() {
         return native_property;
     }

}