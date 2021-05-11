package org.topnetwork.grpclib.pojo.transaction;

import lombok.Data;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Sender_action<T> {

    private String action_authorization;
    private String action_ext;
    private Long action_hash;
    private String action_name;
    private Action_param action_param;
    private Long action_size;
    private Integer action_type;
    private String tx_sender_account_addr;

}