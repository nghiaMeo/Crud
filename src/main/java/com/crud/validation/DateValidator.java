package com.crud.validation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {

    private int min;

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(date)) {
            return true;
        }
        var age = ChronoUnit.YEARS.between(date, LocalDate.now());
        if (age < min) {
            return false;
        }

        LocalDate now = LocalDate.now();
        LocalDate minDate = now.minusYears(100);

        return date.isAfter(minDate) && date.isBefore(now);
    }
}
