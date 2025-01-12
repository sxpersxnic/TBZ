package com.m320.api.lib.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Objects;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
    private String password;
    private String confirmPassword;

    @Override
    public void initialize(ConfirmPassword matching) {
        this.password = matching.password();
        this.confirmPassword = matching.confirmPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        Object pwdValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPwdValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

        return Objects.equals(pwdValue, confirmPwdValue);
    }
}