package org.topnetwork.grpclib.pojo.stream;

import java.io.Serializable;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Body implements Serializable {

    private Tableblock tableblock;
    public void setTableblock(Tableblock tableblock) {
         this.tableblock = tableblock;
     }
     public Tableblock getTableblock() {
         return tableblock;
     }

    @Override
    public String toString() {
        return "Body{" +
                "tableblock=" + tableblock +
                '}';
    }
}