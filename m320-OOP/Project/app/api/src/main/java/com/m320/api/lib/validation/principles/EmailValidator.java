package com.m320.api.lib.validation.principles;

import com.m320.api.lib.validation.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        return Validator.isValidEmail(value);
    }
}