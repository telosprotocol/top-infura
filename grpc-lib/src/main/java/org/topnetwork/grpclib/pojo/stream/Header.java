package org.topnetwork.grpclib.pojo.stream;

import java.io.Serializable;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Header implements Serializable {

    private String auditor_xip;
    private Long timerblock_height;
    private String validator;
    private String validator_xip;
    public void setAuditor_xip(String auditor_xip) {
         this.auditor_xip = auditor_xip;
     }
     public String getAuditor_xip() {
         return auditor_xip;
     }

    public void setTimerblock_height(Long timerblock_height) {
         this.timerblock_height = timerblock_height;
     }
     public Long getTimerblock_height() {
         return timerblock_height;
     }

    public void setValidator(String validator) {
         this.validator = validator;
     }
     public String getValidator() {
         return validator;
     }

    public void setValidator_xip(String validator_xip) {
         this.validator_xip = validator_xip;
     }
     public String getValidator_xip() {
         return validator_xip;
     }

    @Override
    public String toString() {
        return "Header{" +
                "auditor_xip='" + auditor_xip + '\'' +
                ", timerblock_height=" + timerblock_height +
                ", validator='" + validator + '\'' +
                ", validator_xip='" + validator_xip + '\'' +
                '}';
    }
}