package com.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.dto.ErrorResponseDTO;

import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Clase GlobalExceptionHandler: Maneja las excepciones globales de la API, como
 * errores de validación y problemas de base de datos.
 */
@ControllerAdvice
@Tag(name = "Global Exception Handler", description = "Manejo global de excepciones en la API.")
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones de validación de argumentos del método.
     * 
     * @param ex la excepción MethodArgumentNotValidException
     * @return un ResponseEntity con un mapa de errores y un estado HTTP BAD_REQUEST
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Operation(summary = "Manejo de excepciones de validación", description = "Retorna un mapa de errores con detalles de validación.")
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja las excepciones de acceso a datos, de la base de datos H2 en memoria.
     * 
     * @param ex      la excepción DataAccessException
     * @param request la solicitud WebRequest
     * @return un ResponseEntity con un ErrorResponse y un estado HTTP
     *         INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(DataAccessException.class)
    @Operation(summary = "Manejo de excepciones de acceso a datos", description = "Retorna un error de acceso a datos.")
    public ResponseEntity<ErrorResponseDTO> handleDataAccessException(DataAccessException ex, WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Error de acceso a datos: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maneja todas las excepciones globales no controladas.
     * 
     * @param ex      la excepción general
     * @param request la solicitud WebRequest
     * @return un ResponseEntity con un ErrorResponse y un estado HTTP
     *         INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(Exception.class)
    @Operation(summary = "Manejo de excepciones globales", description = "Retorna un error inesperado.")
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Error inesperado: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
