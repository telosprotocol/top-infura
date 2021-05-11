package org.topnetwork.api.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author CasonCai
 * @since 2021/4/30 10:16 上午
 **/
@ApiModel
@Data
@AllArgsConstructor
public class PageData<T>{

    @ApiModelProperty("当前页数")
    private Long pageNum;
    @ApiModelProperty("每页返回数量")
    private Long pageSize;
    @ApiModelProperty("总页数")
    private Long totalPage;
    @ApiModelProperty("总数据量")
    private Long totalSize;
    @ApiModelProperty("数据")
    private List<T> data;

    public static <T> PageData<T> createPage(long pageNum, long pageSize, long totalPage, long totalSize, List<T> data){
        return new PageData<T>(pageNum, pageSize, totalPage, totalSize, data);
    }

}
