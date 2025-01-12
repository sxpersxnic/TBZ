package com.m320.api.lib.validation.password;

import com.m320.api.lib.validation.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        return Validator.isValidPassword(value);
    }
}