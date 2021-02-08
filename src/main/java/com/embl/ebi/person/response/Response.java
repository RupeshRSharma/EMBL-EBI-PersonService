package com.embl.ebi.person.response;

import com.embl.ebi.person.constant.StatusEnum;
import com.embl.ebi.person.exception.Error;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response class for Person services.
 * @param <T>
 *          the type parameter
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class Response<T> {

	/**
	 * Instantiates a new Response with given data.
	 * @param person
	 *          the data
	 */
	public Response(T person) {
		this.setPerson(person);
	}

	/**
	 * The Status.
	 */
	private StatusEnum status;

	/**
	 * The Errors.
	 */
	private List<Error> errors = new ArrayList<>();

	/**
	 * The data
	 */
	private T person;

	/**
	 * Add error to response
	 * @param error
	 */
	public void addError(Error error) {
		this.errors.add(error);
	}

}

