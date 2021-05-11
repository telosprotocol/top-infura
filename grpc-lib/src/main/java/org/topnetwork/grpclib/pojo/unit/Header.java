package org.topnetwork.grpclib.pojo.unit;

import lombok.Data;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Header {

    private String auditor_xip;
    private Long timerblock_height;
    private String validator;
    private String auditor;
    private String validator_xip;

}