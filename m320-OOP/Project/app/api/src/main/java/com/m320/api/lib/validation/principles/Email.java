package com.m320.api.lib.validation.principles;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Email {
    String message() default "Must be valid email!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
