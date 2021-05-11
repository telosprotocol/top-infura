package org.topnetwork.api.bean.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * TableBlock
 * @author CasonCai
 * @since 2021/4/30 10:32 上午
 **/
@ApiModel("tableblock")
@Data
public class TableBlockResp {

    private String owner;

    private Long height;

    private String hash;

    private String preHash;

    private Integer tableId;

    private Integer shardId;

    private Integer clusterId;

    private Integer zoneId;

    private Long timestamp;

    private String auditorXip;

    private Long timerHeight;

    private String validatorAddress;

    private String validatorXip;

    private Integer unitblockCount;
}
