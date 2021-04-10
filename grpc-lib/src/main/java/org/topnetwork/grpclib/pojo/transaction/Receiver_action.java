package org.topnetwork.grpclib.pojo.transaction;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Receiver_action {

    private String action_authorization;
    private String action_ext;
    private int action_hash;
    private String action_name;
    private Action_param action_param;
    private int action_size;
    private int action_type;
    private String tx_receiver_account_addr;

    public String getAction_authorization() {
        return action_authorization;
    }

    public void setAction_authorization(String action_authorization) {
        this.action_authorization = action_authorization;
    }

    public String getAction_ext() {
        return action_ext;
    }

    public void setAction_ext(String action_ext) {
        this.action_ext = action_ext;
    }

    public int getAction_hash() {
        return action_hash;
    }

    public void setAction_hash(int action_hash) {
        this.action_hash = action_hash;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public Action_param getAction_param() {
        return action_param;
    }

    public void setAction_param(Action_param action_param) {
        this.action_param = action_param;
    }

    public int getAction_size() {
        return action_size;
    }

    public void setAction_size(int action_size) {
        this.action_size = action_size;
    }

    public int getAction_type() {
        return action_type;
    }

    public void setAction_type(int action_type) {
        this.action_type = action_type;
    }

    public String getTx_receiver_account_addr() {
        return tx_receiver_account_addr;
    }

    public void setTx_receiver_account_addr(String tx_receiver_account_addr) {
        this.tx_receiver_account_addr = tx_receiver_account_addr;
    }

    @Override
    public String toString() {
        return "Target_action{" +
                "action_authorization='" + action_authorization + '\'' +
                ", action_ext='" + action_ext + '\'' +
                ", action_hash=" + action_hash +
                ", action_name='" + action_name + '\'' +
                ", action_param=" + action_param +
                ", action_size=" + action_size +
                ", action_type=" + action_type +
                ", tx_receiver_account_addr='" + tx_receiver_account_addr + '\'' +
                '}';
    }
}