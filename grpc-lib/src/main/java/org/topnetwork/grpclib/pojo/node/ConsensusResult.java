package org.topnetwork.grpclib.pojo.node;

import java.util.HashMap;
import java.util.List;

/**
 * @description: 审计者和验证者当选
 **/
public class ConsensusResult {
   private HashMap<String, List<Object>> auditor;
   private HashMap<String,List<Object>> validator;

    public HashMap<String, List<Object>> getAuditor() {
        return auditor;
    }

    public void setAuditor(HashMap<String, List<Object>> auditor) {
        this.auditor = auditor;
    }

    public HashMap<String, List<Object>> getValidator() {
        return validator;
    }

    public void setValidator(HashMap<String, List<Object>> validator) {
        this.validator = validator;
    }

    @Override
    public String toString() {
        return "ConsensusResult{" +
                "auditor=" + auditor +
                ", validator=" + validator +
                '}';
    }
}
