package org.topnetwork.grpclib.pojo.unit;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Body {

    private Object fullunit;
    private Lightunit lightunit;

    public Object getFullunit() {
        return fullunit;
    }

    public void setFullunit(Object fullunit) {
        this.fullunit = fullunit;
    }

    public Lightunit getLightunit() {
        return lightunit;
    }

    public void setLightunit(Lightunit lightunit) {
        this.lightunit = lightunit;
    }

    @Override
    public String toString() {

        return "Body{" +
                "fullunit=" + fullunit +
                ", lightunit=" + lightunit +
                '}';
    }
}