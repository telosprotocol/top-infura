package org.topnetwork.grpclib.pojo.node;

import java.util.List;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class StandbyValue {

    private List<StandbyNodeValue> archive;
    private List<StandbyNodeValue> auditor;
    private List<StandbyNodeValue> edge;
    private List<StandbyNodeValue> root_beacon;
    private List<StandbyNodeValue> validator;
    private List<StandbyNodeValue> sub_beacon;

    public List<StandbyNodeValue> getArchive() {
		return archive;
	}

	public void setArchive(List<StandbyNodeValue> archive) {
		this.archive = archive;
	}

	public List<StandbyNodeValue> getAuditor() {
        return auditor;
    }

    public void setAuditor(List<StandbyNodeValue> auditor) {
        this.auditor = auditor;
    }

    public List<StandbyNodeValue> getEdge() {
        return edge;
    }

    public void setEdge(List<StandbyNodeValue> edge) {
        this.edge = edge;
    }

    public List<StandbyNodeValue> getValidator() {
        return validator;
    }

    public void setValidator(List<StandbyNodeValue> validator) {
        this.validator = validator;
    }

    
    public List<StandbyNodeValue> getRoot_beacon() {
		return root_beacon;
	}

	public void setRoot_beacon(List<StandbyNodeValue> root_beacon) {
		this.root_beacon = root_beacon;
	}

	public List<StandbyNodeValue> getSub_beacon() {
		return sub_beacon;
	}

	public void setSub_beacon(List<StandbyNodeValue> sub_beacon) {
		this.sub_beacon = sub_beacon;
	}

	@Override
    public String toString() {
        return "StandbyValue{" +
                "archive=" + archive +
                ", auditor=" + auditor +
                ", edge=" + edge +
                ", root_beacon=" + root_beacon +
                ", validator=" + validator +
                ", sub_beacon=" + sub_beacon +
                '}';
    }
}