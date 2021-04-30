package org.topnetwork.api.bean;

/**
 * @author CasonCai
 * @since 2021/4/30 10:15 上午
 **/
public class CommonResult <T>{

    private long code;

    private String message;

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
