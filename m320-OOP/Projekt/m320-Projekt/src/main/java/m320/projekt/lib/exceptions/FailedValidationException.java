package m320.projekt.lib.exceptions;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class FailedValidationException extends RuntimeException {
    private final Map<String, List<String>> errors;

    public FailedValidationException(Map<String, List<String>> errors) {
        this.errors = errors;
    }

}