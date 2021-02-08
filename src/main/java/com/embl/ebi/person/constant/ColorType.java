package com.embl.ebi.person.constant;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The interface Color type.
 * Note: This is one way of doing validation on model object using Pattern.
 * Other way could be using Enum for the color type and using that to validate input value.
 */
@Pattern(regexp = "blue|red", flags = Pattern.Flag.CASE_INSENSITIVE)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@ReportAsSingleViolation
public @interface ColorType {

  /**
   * Message string.
   * @return the string
   */
  String message() default "Color type is blue|red";

  /**
   * Groups class [ ].
   * @return the class [ ]
   */
  Class<?>[] groups() default {};

  /**
   * Payload class [ ].
   * @return the class [ ]
   */
  Class<? extends Payload>[] payload() default {};

}