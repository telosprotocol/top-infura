package org.topnetwork.grpclib.enums;

public enum NodeType {
    /**
	 * 节点角色类型:
     * auditor：审计者
     * validator：验证者，
     * edge：边缘节点
     * arcs：存档者
     * zecs：根信标委员选举链
     * recs：子信标委员选举链
     */
    advance, validator, edge, arcs,root_beacon,sub_beacon
}
