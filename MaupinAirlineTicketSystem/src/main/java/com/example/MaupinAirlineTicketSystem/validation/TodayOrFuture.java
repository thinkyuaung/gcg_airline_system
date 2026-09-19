package com.example.MaupinAirlineTicketSystem.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Constraint(validatedBy = { TodayOrFuture.LocalDateTimeValidator.class, TodayOrFuture.DateValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TodayOrFuture {
    String message() default "Date must be today or in the future";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};

    class LocalDateTimeValidator implements ConstraintValidator<TodayOrFuture, LocalDateTime> {
        @Override
        public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
            if (value == null) return true;
            return !value.toLocalDate().isBefore(LocalDate.now());
        }
    }

    class DateValidator implements ConstraintValidator<TodayOrFuture, Date> {
        @Override
        public boolean isValid(Date value, ConstraintValidatorContext context) {
            if (value == null) return true;
            return !value.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now());
        }
    }
}
