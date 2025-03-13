package com.github.sxpersxnic.tbz.m320.lib.exceptions;


import org.hibernate.TransientPropertyValueException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sxpersxnic
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({ConversionFailedException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleConversionAndArgumentMismatchExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonMessage(ex.getMessage()));
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> handleResponseStatusExceptions(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(new JsonMessage(ex.getReason()));
    }

    @ExceptionHandler({FailedValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleValidationExceptions(Exception ex) {
        Map<String, List<String>> errors = new HashMap<>();

        if (ex instanceof FailedValidationException failedValidationException) {
            errors.putAll(failedValidationException.getErrors());
        }

        if (ex instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();

                if (!errors.containsKey(fieldName))
                    errors.put(fieldName, new ArrayList<>());

                errors.get(fieldName).add(errorMessage);
            });
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> handleAccessDeniedExceptions(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonMessage(ex.getMessage()));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleRuntimeExceptions(RuntimeException ex) {
        if (ex instanceof InvalidDataAccessApiUsageException) {
            if (ex.getCause() != null && ex.getCause().getCause() instanceof TransientPropertyValueException tex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonMessage(tex.getPropertyName() + " must be valid"));
            }
        } else if (ex instanceof AuthorizationDeniedException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonMessage(ex.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonMessage("Internal server error. message: " + ex.getMessage()));
    }

    // Strings don't get serialized in ResponseEntity body so with this record it gets serialized as json
    private record JsonMessage(String message) {
    }

}
