package org.topnetwork.grpclib.pojo.stream;
import java.io.Serializable;
import java.util.List;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class LightUnitState implements Serializable {

    private int balance_change;
    private int burned_amount_change;
    private String custom_property_key;
    private List<String> native_property;
    public void setBalance_change(int balance_change) {
         this.balance_change = balance_change;
     }
     public int getBalance_change() {
         return balance_change;
     }

    public void setBurned_amount_change(int burned_amount_change) {
         this.burned_amount_change = burned_amount_change;
     }
     public int getBurned_amount_change() {
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