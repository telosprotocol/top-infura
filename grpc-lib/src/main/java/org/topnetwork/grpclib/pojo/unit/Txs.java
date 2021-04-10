package org.topnetwork.grpclib.pojo.unit;

import java.util.HashMap;
import java.util.List;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Txs {

    private List<HashMap<String,TxDetail>> list;

    public List<HashMap<String, TxDetail>> getList() {
        return list;
    }

    public void setList(List<HashMap<String, TxDetail>> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Txs{" +
                "list=" + list +
                '}';
    }
}