package com.embl.ebi.person.service;

import com.embl.ebi.person.exception.PersonException;
import com.embl.ebi.person.response.Response;
import com.embl.ebi.person.model.Person;
import java.util.List;
import java.util.Map;

/**
 * Service class for Person related operations.
 */
public interface PersonService {

	/**
	 * Create Person in database with the details.
	 * @param person
	 *          Person details
	 * @return Response having created Person in database with unique Person id
	 */
	Response<Person> createPerson(Person person);

	/**
	 * Update a person with input data as a map
	 * @param personId
	 *          Unique id of the person
	 * @param propMap
	 *          Map of data as key/value
	 * @return Empty response
	 */
	Response<Void> updatePerson(String personId, Map <String, String> propMap) throws PersonException;

	/**
	 * Get Person details for the given Person id
	 * @param personId
	 *          Unique id of the Person
	 * @return Response having Person details
	 */
	Response<Person> getPerson(String personId) throws PersonException;

	/**
	 * Delete a Person by a given Id
	 * @param personId Id of the person
	 * @return Empty response
	 */
	Response<Void> deletePerson(String personId) throws PersonException;

	/**
	 * Get Person list from database
	 *
	 * @return Response having Person list
	 */
	Response<List<Person>> getPersonList() throws PersonException;

}
