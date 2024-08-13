package com.crud.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidDate {
    String message() default "Date is not valid";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
