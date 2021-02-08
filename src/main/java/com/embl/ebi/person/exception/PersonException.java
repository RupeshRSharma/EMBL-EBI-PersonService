package com.embl.ebi.person.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Exception for Person services
 */
@Getter
public class PersonException extends Exception {

	// Error code
	private final String errorCode;

	// Errors
	private List<Error> errors = new ArrayList();

	public PersonException(String errorCode) {
		this.errorCode = errorCode;
	}

	public PersonException(String errorCode, List<Error> errors) {
		this.errorCode = errorCode;
		this.errors = errors;
	}

	public PersonException(String errorCode, String args) {
		super(args);
		this.errorCode = errorCode;
	}

	public PersonException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public PersonException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

}
