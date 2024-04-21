package com.challenge.smarti.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.challenge.smarti.mapper.ConstantsHelper.C_2;
import static com.challenge.smarti.mapper.ConstantsHelper.WEBINT;

public class InterfaceTypeValidator implements ConstraintValidator<ValidInterfaceType,String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.equals(C_2) || value.equals(WEBINT));
    }
}
