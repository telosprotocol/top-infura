package org.topnetwork.api.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CasonCai
 * @since 2021/4/30 10:15 上午
 **/
@ApiModel
@Data
public class CommonResult <T>{
    @ApiModelProperty(value = "返回代码")
    private long code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult success(){
        return new CommonResult(1, "success", null);
    }


    public static <T> CommonResult<T> success(T data){
        return new CommonResult(1, "success", data);
    }

}
