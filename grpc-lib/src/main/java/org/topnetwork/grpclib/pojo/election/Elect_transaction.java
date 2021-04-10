package org.topnetwork.grpclib.pojo.election;
import java.math.BigInteger;
import java.util.List;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Elect_transaction {

    private List<Elect_nodes> elect_nodes;
    private BigInteger round_no;
    private BigInteger zone_id;
    public void setElect_nodes(List<Elect_nodes> elect_nodes) {
         this.elect_nodes = elect_nodes;
     }
     public List<Elect_nodes> getElect_nodes() {
         return elect_nodes;
     }

    public void setRound_no(BigInteger round_no) {
         this.round_no = round_no;
     }
     public BigInteger getRound_no() {
         return round_no;
     }

    public void setZone_id(BigInteger zone_id) {
         this.zone_id = zone_id;
     }
     public BigInteger getZone_id() {
         return zone_id;
     }

}