package org.topnetwork.grpclib.pojo.node;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class StandbyNodeValue {

    private String consensus_public_key;
    
    private String node_id;
    
    private int stake;
    
  
	public String getConsensus_public_key() {
		return consensus_public_key;
	}
	public void setConsensus_public_key(String consensus_public_key) {
		this.consensus_public_key = consensus_public_key;
	}
	public String getNode_id() {
		return node_id;
	}
	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}
	public void setStake(int stake) {
         this.stake = stake;
     }
     public int getStake() {
         return stake;
     }

}