package org.topnetwork.grpclib.pojo.election;

import java.util.HashMap;
import java.util.List;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Lightunit_input {

    private List<HashMap<String, Object>> txs;

    public List<HashMap<String, Object>> getTxs() {
        return txs;
    }

    public void setTxs(List<HashMap<String, Object>> txs) {
        this.txs = txs;
    }
}