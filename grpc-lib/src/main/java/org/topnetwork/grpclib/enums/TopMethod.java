package org.topnetwork.grpclib.enums;

public enum TopMethod {
    REQUEST_TOKEN(1, "request_token"),
    GET_PROPERTY(2,"get_property"),
    ACCOUNT_INFO(3,"account_info"),
    ACCOUNT_TRANSACTION(4,"account_transaction"),
    GET_BLOCK(5,"get_block"),
    GET_BLOCK_STREAM(6,"stream_table"),
    GET_TRANSACTION(7,"get_transaction"),
    GET_GENERAL_INFOS(8,"get_general_infos"),
    GET_ACCOUNT(9,"get_account");

    TopMethod(int code, String methodName) {
        this.code = code;
        this.methodName = methodName;
    }

    private Integer code;
    private String methodName;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
