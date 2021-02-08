package com.embl.ebi.person.controller;

import com.embl.ebi.person.constant.EndpointConstants;
import com.embl.ebi.person.exception.PersonException;
import com.embl.ebi.person.model.Person;
import com.embl.ebi.person.response.Response;
import com.embl.ebi.person.service.PersonService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Person service endpoints.
 * <p>
 * 1. /1.0/persons:POST - Create a Person in database with given details.
 * </p>
 * <p>
 * 2. /1.0/persons/{personId}:PUT - Update details of a Person in database with given id and properties.
 * </p>
 * <p>
 * 3. /1.0/persons/{personId}:GET - Get details of a Person from database with given id.
 * </p>
 * <p>
 * 4. /1.0/persons/{personId}:DELETE - Delete a Person from database with given id.
 * </p>
 * 5. /1.0/persons:GET - Get list of Persons from database.
 * </p>
 */
@RestController
@RequestMapping(EndpointConstants.BASE_V1_API + EndpointConstants.PERSONS)
@Log4j2
@AllArgsConstructor
@Validated
public class PersonController {

	// Person service
	private final PersonService personService;

	/**
	 * Create a Person in database with given details
	 *
	 * @return Response with Http status 200 and Person details
	 * @throws PersonException Any exception
	 */
	@PostMapping
	public ResponseEntity<Response<Person>> createPerson(@Valid @RequestBody Person person) throws PersonException {

		log.info("Create Person {} in database", person);

		return ResponseEntity.ok(personService.createPerson(person));
	}

	/**
	 * Update details of a Person in database with given id and properties.
	 *
	 * @param personId Id of the person to be updated
	 * @param propMap Properties to be updated
	 * @return Empty Response with Http status 200
	 * @throws PersonException Any exception
	 */
	@PutMapping("/{personId}")
	public ResponseEntity<Response<Void>> updatePerson(@PathVariable String personId,  @RequestBody final Map<String, String> propMap) throws PersonException {

		log.debug("Update Person details for id {}", personId);

		return ResponseEntity.ok(personService.updatePerson(personId, propMap));
	}

	/**
	 * Get details of a Person from database with given id.
	 *
	 * @param personId Id of the person
	 * @return Response with Http status 200 and Person details
	 * @throws PersonException Any exception
	 */
	@GetMapping("/{personId}")
	public ResponseEntity<Response<Person>> getPerson(@PathVariable String personId) throws PersonException {

		log.info("Get Person details with {} from database", personId);

		return ResponseEntity.ok(personService.getPerson(personId));
	}

	/**
	 * Delete a Person from database with given id.
	 *
	 * @param personId Id of the person to be deleted
	 * @return Empty Response with Http status 200
	 * @throws PersonException Any exception
	 */
	@DeleteMapping("/{personId}")
	public ResponseEntity<Response<Void>> deletePerson(@PathVariable String personId) throws PersonException {

		log.info("Delete Person with id {} from database", personId);

		return ResponseEntity.ok(personService.deletePerson(personId));
	}

	/**
	 * Get list of Persons from database.
	 *
	 * @return Response with Http status 200 and Person list
	 * @throws PersonException Any exception
	 */
	@GetMapping
	public ResponseEntity<Response<List<Person>>> getPersonList() throws PersonException {

		log.info("Get Person list with from database");

		return ResponseEntity.ok(personService.getPersonList());
	}

}
