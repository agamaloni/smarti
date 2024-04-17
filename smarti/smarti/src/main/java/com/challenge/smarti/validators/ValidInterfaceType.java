package com.challenge.smarti.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InterfaceTypeValidator.class)
public @interface ValidInterfaceType {
    String message() default "Invalid InterfaceType. Allowed only valid interface types";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}