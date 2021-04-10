package org.topnetwork.grpclib.pojo.transaction;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Tx_action {

    private Sender_action sender_action;
    private Receiver_action receiver_action ;

    public Sender_action getSender_action() {
        return sender_action;
    }

    public void setSender_action(Sender_action sender_action) {
        this.sender_action = sender_action;
    }

    public Receiver_action getReceiver_action() {
        return receiver_action;
    }

    public void setReceiver_action(Receiver_action receiver_action) {
        this.receiver_action = receiver_action;
    }

    @Override
    public String toString() {
        return "Tx_action{" +
                "sender_action=" + sender_action +
                ", receiver_action=" + receiver_action +
                '}';
    }
}