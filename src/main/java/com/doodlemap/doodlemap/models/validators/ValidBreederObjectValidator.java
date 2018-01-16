package com.doodlemap.doodlemap.models.validators;

import com.doodlemap.doodlemap.models.Breeder;
import com.doodlemap.doodlemap.models.forms.EditBreederForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidBreederObjectValidator implements ConstraintValidator<ValidBreederObject, EditBreederForm> {

        @Override
        public void initialize(ValidBreederObject constraintAnnotation) {
        }

        @Override
        public boolean isValid(EditBreederForm form, ConstraintValidatorContext context) {
            if ( form == null ) {
                return true;
            }

            return form.getBreeder().getName() != "";
        }
    }

