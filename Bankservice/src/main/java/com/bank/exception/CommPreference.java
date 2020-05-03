package com.bank.exception;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
import javax.validation.Constraint;
import javax.validation.Payload;
 
 
 
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommPreferenceValidator.class)
@Documented
public @interface CommPreference {
 
    String message() default "Communication preference must be email or mobilePhone.";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}

