package com.embl.ebi.person.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception codes for Person service
 */
@AllArgsConstructor
@Getter
public enum PersonExceptionCode {

	// Invalid Person id present in the request
	INVALID_PERSON_ID("PS_101"),

	// Invalid input present in the request
	INVALID_INPUT("PS_201");

	// Exception code
	private final String code;

}
