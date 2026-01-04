package com.leocarlos10.backendSG_medica.exception;

public class UnauthorizedException extends RuntimeException {

    private String code;

    public UnauthorizedException(String message) {
        super(message);
        this.code = "UNAUTHORIZED";
    }

    public String getCode() {
        return code;
    }
}
