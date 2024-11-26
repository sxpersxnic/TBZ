package m320.projekt.lib.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.TransientPropertyValueException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({ConversionFailedException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleConversionAndArgumentMismatchExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + new JsonMessage(ex.getMessage()) + "\nStatus: " + HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> handleResponseStatusExceptions(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body("Error: " + new JsonMessage(ex.getReason()) + "\nStatus: " + ex.getStatusCode());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + new JsonMessage(ex.getMessage()) + "\nStatus: " + HttpStatus.NOT_FOUND.value());
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

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleRuntimeExceptions(RuntimeException ex) {
        if (ex instanceof InvalidDataAccessApiUsageException) {
            if (ex.getCause() != null && ex.getCause().getCause() instanceof TransientPropertyValueException tex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonMessage(tex.getPropertyName() + " must be valid"));
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonMessage(ex.getMessage()));
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> handleAccessDeniedExceptions(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonMessage(ex.getMessage()));
    }

    // Strings don't get serialized in ResponseEntity body so with this record it gets serialized as json
    private record JsonMessage(String message) {
    }
}
