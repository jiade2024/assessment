package com.jd.assessment.enums;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 10:43
 */
public enum ResultCode {
    SUCCESS(200, "SUCCESS"),

    NOT_FOUND(404, "RESOURCE NOT FOUND"),

    PARAMETER_ERROR(405, "PARAMETER ERROR"),

    SERVER_ERROR(500, "SERVER INTERNAL ERROR")

    ;

    private final int code;

    private final String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
