package com.doodlemap.doodlemap.models.validators;


//import org.hibernate.annotations.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidBreederObjectValidator.class })
@Documented
public @interface ValidBreederObject {

    String message() default "Not a valid Breeder";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
