/**
 * 
 */
package org.topnetwork.common.enums;
public enum ChainZoneType {
	
	CHAIN_ZONE_CONSENSUS_INDEX(0),//other,所有shard
	CHAIN_ZONE_BEACON_INDEX(1),//beacon
	CHAIN_ZONE_ZEC_INDEX(2);//zec
	int index;
	
	ChainZoneType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public static ChainZoneType getChainZoneType(int index) {
		for (ChainZoneType type : ChainZoneType.values()) {
			if (type.getIndex() == index) {
				return type;
			}
		}
		return ChainZoneType.CHAIN_ZONE_CONSENSUS_INDEX;
	}
}
