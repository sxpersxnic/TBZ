package com.m320.api.lib.validation.principles;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UsernameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Username {
    String message() default "Must be valid username!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
