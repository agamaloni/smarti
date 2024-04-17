package com.challenge.smarti.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InterfaceTypeValidator implements ConstraintValidator<ValidInterfaceType,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.equals("c2") || value.equals("webint"));
    }
}
