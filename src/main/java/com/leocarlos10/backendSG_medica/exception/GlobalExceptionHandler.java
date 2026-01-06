package com.leocarlos10.backendSG_medica.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.leocarlos10.backendSG_medica.dto.respuestasComunes.Response;
import com.leocarlos10.backendSG_medica.dto.respuestasComunes.ValidationErrorDetail;

import java.sql.SQLException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Maneja excepciones de negocio personalizadas
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response<Object>> handleBusinessException(BusinessException ex, WebRequest request) {
        logger.warn("BusinessException: {}", ex.getMessage());
        Response<Object> response = Response.builder()
                .responseCode(HttpStatus.BAD_REQUEST.value())
                .responseMessage(ex.getMessage())
                .data(ex.getData())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja excepciones cuando una entidad no se encuentra
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response<Object>> handleEntityNotFoundException(EntityNotFoundException ex,
            WebRequest request) {
        logger.warn("EntityNotFoundException: {}", ex.getMessage());
        Response<Object> response = Response.builder()
                .responseCode(HttpStatus.NOT_FOUND.value())
                .responseMessage(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Maneja excepciones de validación personalizadas
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response<Object>> handleValidationException(ValidationException ex, WebRequest request) {
        logger.warn("ValidationException: {}", ex.getMessage());
        Response<Object> response = Response.builder()
                .responseCode(HttpStatus.BAD_REQUEST.value())
                .responseMessage(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja excepciones de validación de Jakarta (Bean Validation)
     * Se activa cuando @Valid falla en los DTOs/Models
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            WebRequest request) {
        logger.warn("MethodArgumentNotValidException: Errores de validación detectados");

        List<ValidationErrorDetail> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> ValidationErrorDetail.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .toList();

        Response<Object> response = Response.builder()
                .responseCode(HttpStatus.BAD_REQUEST.value())
                .responseMessage("Errores de validación en los datos enviados")
                .errorList(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja excepciones de autenticación/autorización
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Response<Object>> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        logger.warn("UnauthorizedException: {}", ex.getMessage());
        Response<Object> response = Response.builder()
                .responseCode(HttpStatus.UNAUTHORIZED.value())
                .responseMessage(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    /**
     * Maneja excepciones de SQL
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Response<Object>> handleSQLException(SQLException ex, WebRequest request) {
        logger.error("SQLException: ", ex);
        Response<Object> response = Response.builder()
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .responseMessage("Error en la base de datos")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Maneja excepciones genéricas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Exception: ", ex);
        Response<Object> response = Response.builder()
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .responseMessage("Error interno del servidor")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
