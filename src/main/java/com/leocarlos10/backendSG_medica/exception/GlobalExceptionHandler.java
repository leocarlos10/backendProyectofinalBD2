package com.leocarlos10.backendSG_medica.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.leocarlos10.backendSG_medica.dto.respuestasComunes.ApiResponse;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Maneja excepciones de negocio personalizadas
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex, WebRequest request) {
        logger.warn("BusinessException: {}", ex.getMessage());
        ApiResponse<?> response = new ApiResponse<>(false, ex.getCode(), ex.getMessage(), ex.getData());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja excepciones cuando una entidad no se encuentra
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEntityNotFoundException(EntityNotFoundException ex,
            WebRequest request) {
        logger.warn("EntityNotFoundException: {}", ex.getMessage());
        ApiResponse<?> response = new ApiResponse<>(false, ex.getCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Maneja excepciones de validación
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(ValidationException ex, WebRequest request) {
        logger.warn("ValidationException: {}", ex.getMessage());
        ApiResponse<?> response = new ApiResponse<>(false, ex.getCode(), ex.getMessage(), null, ex.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja excepciones de autenticación/autorización
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<?>> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        logger.warn("UnauthorizedException: {}", ex.getMessage());
        ApiResponse<?> response = new ApiResponse<>(false, ex.getCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    /**
     * Maneja excepciones de SQL
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiResponse<?>> handleSQLException(SQLException ex, WebRequest request) {
        logger.error("SQLException: ", ex);
        ApiResponse<?> response = new ApiResponse<>(false, "DB_ERROR", "Error en la base de datos");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Maneja excepciones genéricas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Exception: ", ex);
        ApiResponse<?> response = new ApiResponse<>(false, "INTERNAL_ERROR", "Error interno del servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
