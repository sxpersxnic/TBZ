package com.m320.api.lib.validation;

import com.m320.api.lib.exceptions.ExceptionMessages;
import com.m320.api.lib.exceptions.FailedValidationException;
import com.m320.api.model.Profile;
import com.m320.api.model.User;
import com.m320.api.payload.dto.request.auth.SignUpRequestDTO;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.m320.api.lib.constants.Regex.*;

/**
 * Utility class for validating authentication inputs using regular expressions.
 */
public class Validator {

    /**
     * Validates the credentials provided in a sign-up request.
     *
     * @param dto  The sign-up request DTO.
     * @param user The user object to update.
     * @param profile The profile object to update.
     * @throws FailedValidationException if the credentials are invalid.
     */
    public static User validateCredentials(SignUpRequestDTO dto, User user, Profile profile) throws FailedValidationException {
        Map<String, List<String>> errors = new HashMap<>();

        validateField(dto.getEmail(), EMAIL_PATTERN, "Email", errors);
        validateField(dto.getUsername(), USERNAME_PATTERN, "Username", errors);
        validateField(dto.getPassword(), PASSWORD_PATTERN, "Password", errors);

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        } else {
            profile.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            return user;
        }
    }

    private static void validateField(String value, Pattern pattern, String fieldName, Map<String, List<String>> errors) {
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            errors.put(fieldName, List.of(ExceptionMessages.getInvalidMessage(fieldName)));
        }
    }

    public static Boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_REGEX, username);
    }
    public static Boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
    public static Boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}