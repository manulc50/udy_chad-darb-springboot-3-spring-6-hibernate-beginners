package com.mlorenzo.springbootdemo.mvc.annotations;

import com.mlorenzo.springbootdemo.mvc.validators.CourseCodeConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target(ElementType.FIELD)
// Retain this annotation in the Java class file. Process it at runtime by the JVM.
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
    // Define default course code
    String value() default "LUV";

    // Define default error message
    String message() default "Must start with LUV";

    // Define default groups
    Class<?>[] groups() default  {};

    // Define default payload
    Class<? extends Payload>[] payload() default {};
}
