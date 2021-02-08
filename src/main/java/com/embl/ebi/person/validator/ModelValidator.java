package com.embl.ebi.person.validator;

import com.embl.ebi.person.exception.PersonException;
import com.embl.ebi.person.exception.PersonExceptionCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.extern.log4j.Log4j2;

/**
 * The type Model validator.
 */
@Log4j2
public class ModelValidator {

  private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private static Validator validator = factory.getValidator();

  /**
   * Validate.
   * @param obj
   *          the obj
   * @throws PersonException
   *           the input validation exception
   */
  public static void validate(Object obj) throws PersonException {

    Set<ConstraintViolation<Object>> violations = validator.validate(obj);
    log.debug("Validation of {} result: {}", obj, violations);
    if (violations != null && !violations.isEmpty()) {
      log.info("Validation errors for {} are: {}", obj, violations);
      List<String> errors = new ArrayList<>();
      violations.forEach(v -> errors.add(v.getMessage()));
      throw new PersonException(PersonExceptionCode.INVALID_INPUT.getCode(), errors.toString());
    }
  }

}
