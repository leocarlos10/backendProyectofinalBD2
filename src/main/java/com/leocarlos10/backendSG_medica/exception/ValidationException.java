package com.leocarlos10.backendSG_medica.exception;

import java.util.Map;

public class ValidationException extends RuntimeException {

    private String code;
    private Map<String, String> errors;

    public ValidationException(String message) {
        super(message);
        this.code = "VALIDATION_ERROR";
    }

    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.code = "VALIDATION_ERROR";
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
