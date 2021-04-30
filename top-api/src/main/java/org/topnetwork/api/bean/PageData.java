package org.topnetwork.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @author CasonCai
 * @since 2021/4/30 10:16 上午
 **/
@Data
public class PageData<T>{

    private Integer pageNum;

    private Integer pageSize;

    private Integer totalPage;

    private Long totalSize;

    private List<T> data;


}
