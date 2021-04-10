package org.topnetwork.grpclib.pojo.unit;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Header {

    private String auditor_xip;
    private int timerblock_height;
    private String validator;
    private String auditor;
    private String validator_xip;

    public void setAuditor_xip(String auditor_xip) {
        this.auditor_xip = auditor_xip;
    }

    public String getAuditor_xip() {
        return auditor_xip;
    }

    public void setTimerblock_height(int timerblock_height) {
        this.timerblock_height = timerblock_height;
    }

    public int getTimerblock_height() {
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

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }
}