package org.topnetwork.grpclib.pojo.election;
import java.math.BigInteger;
import java.util.List;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Lightunit_state {

    private BigInteger balance_change;
    private BigInteger burned_amount_change;
    private String custom_property_key;
    private List<String> native_property;
    public void setBalance_change(BigInteger balance_change) {
         this.balance_change = balance_change;
     }
     public BigInteger getBalance_change() {
         return balance_change;
     }

    public void setBurned_amount_change(BigInteger burned_amount_change) {
         this.burned_amount_change = burned_amount_change;
     }
     public BigInteger getBurned_amount_change() {
         return burned_amount_change;
     }

    public void setCustom_property_key(String custom_property_key) {
         this.custom_property_key = custom_property_key;
     }
     public String getCustom_property_key() {
         return custom_property_key;
     }

    public void setNative_property(List<String> native_property) {
         this.native_property = native_property;
     }
     public List<String> getNative_property() {
         return native_property;
     }

}