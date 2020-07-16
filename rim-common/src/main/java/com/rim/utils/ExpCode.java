package com.rim.utils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author rickiyang
 * @date 2020-05-26
 * @Desc 业务错误码设计
 */
public enum ExpCode {

    SUCCESSFUL(0, "Successful"),
    //通用错误码 1001-1099
    BALANCE_INSUFFICIENT(1001, "余额不足"),
    NOT_DEAL_IDENTIFICATION(1002, "未进行真人认证"),
    PARAM_ERR(1003, "参数错误"),
    BALANCE_NOT_ENOUGH(1004, "账户余额不足"),
    REQUEST_EXP(1005, "请求异常");


    private int code;
    private String message;

    ExpCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Optional<ExpCode> codeOf(int code) {

        return Arrays.stream(ExpCode.values()).filter(expCode -> code == expCode.getCode()).findAny();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
