package com.leocarlos10.backendSG_medica.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String mensaje;
    private T data;
    private String code;
    private LocalDateTime timestamp;
    private Object errors;

    // Constructor para respuestas exitosas
    public ApiResponse(boolean success, String mensaje, T data) {
        this.success = success;
        this.mensaje = mensaje;
        this.data = data;
        this.code = success ? "SUCCESS" : "ERROR";
        this.timestamp = LocalDateTime.now();
    }

    // Constructor para respuestas con código
    public ApiResponse(boolean success, String code, String mensaje, T data) {
        this.success = success;
        this.code = code;
        this.mensaje = mensaje;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // Constructor para errores
    public ApiResponse(boolean success, String code, String mensaje) {
        this.success = success;
        this.code = code;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

    // Constructor completo
    public ApiResponse(boolean success, String code, String mensaje, T data, Object errors) {
        this.success = success;
        this.code = code;
        this.mensaje = mensaje;
        this.data = data;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    // Factory methods para facilitar creación
    public static <T> ApiResponse<T> success(String mensaje, T data) {
        return new ApiResponse<>(true, "SUCCESS", mensaje, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "SUCCESS", "Operación completada exitosamente", data);
    }

    public static <T> ApiResponse<T> error(String code, String mensaje) {
        return new ApiResponse<>(false, code, mensaje);
    }

    public static <T> ApiResponse<T> error(String mensaje) {
        return new ApiResponse<>(false, "ERROR", mensaje);
    }
}
