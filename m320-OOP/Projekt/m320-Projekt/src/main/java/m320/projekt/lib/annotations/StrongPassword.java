package m320.projekt.lib.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import m320.projekt.lib.validation.password.StrongPasswordValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
    String message() default "Must be 8 characters long and combination of at least one uppercase, one lowercase, one digit, one special character!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

