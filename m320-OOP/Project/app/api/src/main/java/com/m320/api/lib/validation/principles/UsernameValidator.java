package com.m320.api.lib.validation.principles;

import com.m320.api.lib.validation.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        return Validator.isValidUsername(value);
    }
}