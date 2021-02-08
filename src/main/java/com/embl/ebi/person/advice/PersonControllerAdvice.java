package com.embl.ebi.person.advice;

import com.embl.ebi.person.constant.StatusEnum;
import com.embl.ebi.person.exception.Error;
import com.embl.ebi.person.exception.ErrorCodes;
import com.embl.ebi.person.exception.PersonException;
import com.embl.ebi.person.response.Response;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler for PersonController.
 */
@ControllerAdvice
@Log4j2
@AllArgsConstructor
public class PersonControllerAdvice {

	private final ErrorCodes errorCodes; // Error codes

	/**
	 * Handle PersonException, return Http status 200 OK with error details.
	 * @param exception
	 *          {@link PersonException}
	 * @return Response entity with Http status 200 OK and error details
	 */
	@ExceptionHandler(PersonException.class)
	ResponseEntity<Response> handlePersonException(PersonException exception){

		Response response = new Response();
		response.setStatus(StatusEnum.FAILURE);
		response.addError(errorCodes.getError(exception.getErrorCode()));

		log.error("Person Exception Occurred:", exception);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Handle validation exceptions response entity.
	 * @param ex
	 *          the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Response<Object>> handleValidationExceptions(ConstraintViolationException ex) {

		Response<Object> responseObject = new Response<>();
		List<String> errors = new ArrayList<>();
		responseObject.setStatus(StatusEnum.FAILURE);
		ex.getConstraintViolations().forEach(error -> errors.add(error.getMessage()));

		populateResponseError(responseObject,
				"Request validation failed :" + errors.toString(), "PS_201", Error.SeverityEnum.FATAL);

		log.error("Request validation failed :", ex);
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}


	/**
	 * Handle validation exceptions response entity.
	 * @param ex
	 *          the ex
	 * @return the response entity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Response<Object> responseObject = new Response<>();
		List<String> errors = new ArrayList<>();
		responseObject.setStatus(StatusEnum.FAILURE);
		ex.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

		populateResponseError(responseObject,
				"Request validation failed :" + errors.toString(), "PS_201", Error.SeverityEnum.FATAL);

		log.error("Request validation failed :", ex);
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}

	/**
	 * Populate response error.
	 * @param <T>
	 *          the type parameter
	 * @param response
	 *          the response
	 * @param errorMessage
	 *          the error message
	 * @param errorCode
	 *          the error code
	 * @param severity
	 *          the severity
	 */
	private <T extends Response> void populateResponseError(T response, String errorMessage,
															String errorCode, Error.SeverityEnum severity) {
		Error error = new Error();
		error.setCode(errorCode);
		error.setMessage(errorMessage);
		error.setSeverity(severity);
		response.getErrors().add(error);
	}


}