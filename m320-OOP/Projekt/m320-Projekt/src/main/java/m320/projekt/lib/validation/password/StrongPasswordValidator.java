package m320.projekt.lib.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import m320.projekt.lib.annotations.StrongPassword;

import static m320.projekt.lib.constants.Regex.PASSWORD_REGEX;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        return value.matches(PASSWORD_REGEX);
    }
}