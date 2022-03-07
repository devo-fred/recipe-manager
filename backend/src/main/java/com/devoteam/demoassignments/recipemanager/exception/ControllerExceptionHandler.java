package com.devoteam.demoassignments.recipemanager.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.devoteam.demoassignments.recipemanager.exception.dto.ResponseDTO;
import com.devoteam.demoassignments.recipemanager.exception.dto.StatusErrorDTO;
import com.devoteam.demoassignments.recipemanager.exception.dto.ValidationErrorDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<StatusErrorDTO> handleResponseStatusException(ResponseStatusException exception) {
        logger.error(exception.getMessage());
        StatusErrorDTO statusErrorDTO = new StatusErrorDTO();

        HttpStatus httpStatus = exception.getStatus();
        statusErrorDTO.setStatusCode(httpStatus.value());
        if (httpStatus.isError()) {
            statusErrorDTO.setError(httpStatus.getReasonPhrase());
        }
        if (!Objects.requireNonNull(exception.getReason()).isBlank()) {
            statusErrorDTO.setMessage(exception.getReason());
        }

        return ResponseEntity.status(httpStatus).headers(HttpHeaders.EMPTY).body(statusErrorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException exception) {
        logger.error(exception.getMessage());
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();

        Map<String, String> fieldErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });
        validationErrorDTO.setMessage("Validation errors");
        validationErrorDTO.setFieldErrors(fieldErrors);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(HttpHeaders.EMPTY).body(validationErrorDTO);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException exception) {
        logger.error(exception.getMessage());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Recipe contains one or more fields with invalid data");
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(HttpHeaders.EMPTY).body(responseDTO);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDTO> handleHttpMessageNotReadableExceptions(AccessDeniedException exception) {
        logger.error(exception.getMessage());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(exception.getMessage());
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HttpHeaders.EMPTY).body(responseDTO);
    }

}