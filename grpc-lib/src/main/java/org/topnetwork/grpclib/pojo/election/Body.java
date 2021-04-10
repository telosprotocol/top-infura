package org.topnetwork.grpclib.pojo.election;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Body {

    private Elect_transaction elect_transaction;
    private Object fullunit;
    private Lightunit lightunit;
    public void setElect_transaction(Elect_transaction elect_transaction) {
         this.elect_transaction = elect_transaction;
     }
     public Elect_transaction getElect_transaction() {
         return elect_transaction;
     }

    public Object getFullunit() {
        return fullunit;
    }

    public void setFullunit(Object fullunit) {
        this.fullunit = fullunit;
    }

    public void setLightunit(Lightunit lightunit) {
         this.lightunit = lightunit;
     }
     public Lightunit getLightunit() {
         return lightunit;
     }

}