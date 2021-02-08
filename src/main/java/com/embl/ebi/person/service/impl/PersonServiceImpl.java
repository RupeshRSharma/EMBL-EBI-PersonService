package com.embl.ebi.person.service.impl;

import com.embl.ebi.person.entity.PersonEntity;
import com.embl.ebi.person.exception.PersonException;
import com.embl.ebi.person.exception.PersonExceptionCode;
import com.embl.ebi.person.mapper.PersonMapper;
import com.embl.ebi.person.model.Person;
import com.embl.ebi.person.repository.PersonRepository;
import com.embl.ebi.person.response.Response;
import com.embl.ebi.person.service.PersonService;
import com.embl.ebi.person.util.CommonUtil;
import com.embl.ebi.person.validator.ModelValidator;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service implementation class for Person.
 */
@Service
@AllArgsConstructor
@Log4j2
public class PersonServiceImpl implements PersonService {

	// JPA repository for Person entity
	private final PersonRepository personRepository;

	// Mapper to map to/from PersonEntity from/to Person model class
	private final PersonMapper mapper;

	/**
	 * Create Person in database with the details.
	 *
	 * @param person Person details
	 *
	 * @return Response having created Person in database with unique Person id
	 */
	@Override
	public Response<Person> createPerson(Person person) {

		log.debug("Creating Person {} in database.", person);

		// Map to entity from model object and generate UID
		PersonEntity personEntity = mapper.mapToEntity(person);
		personEntity.setUid(CommonUtil.generateUid());

		/*
		 * Create Person in database using repository. Map created entity back to model object
		 * for response. Generate the response and return.
		 */
		return CommonUtil
				.generateSuccessResponse(mapper.mapToDto(personRepository.save(personEntity)));
	}

	/**
	 * Update a person with input data as a map
	 *
	 * @param personId Unique id of the person
	 * @param propMap  Map of data as key/value
	 *
	 * @return Empty response
	 */
	@Override
	public Response<Void> updatePerson(String personId, Map<String, String> propMap) throws PersonException {

		log.debug("Update Person with id {} and details {}", propMap);

		// Get Person from database for the given id, throw exception if not found
		PersonEntity personEntity = personRepository.findByUid(personId).orElseThrow(
				() -> new PersonException(PersonExceptionCode.INVALID_PERSON_ID.getCode()));

		log.info("Person with id {} found in database.", personId);

		// Map the input data to the entity
		mapper.map(personEntity, propMap);

		// Validate the entity data by mapping it to model object
		ModelValidator.validate(mapper.mapToDto(personEntity));

		// Update the entity in database
		personRepository.save(personEntity);

		log.info("Person with id {} updated in database.", personId);

		// Generate empty response and return
		return CommonUtil.generateSuccessResponse();

	}

	/**
	 * Get Person details for the given Person id
	 *
	 * @param personId Unique id of the Person
	 *
	 * @return Response having Person details
	 */
	@Override
	public Response<Person> getPerson(String personId) throws PersonException {

		// Get Person from database for the given id, throw exception if not found
		PersonEntity personEntity = personRepository.findByUid(personId).orElseThrow(
				() -> new PersonException(PersonExceptionCode.INVALID_PERSON_ID.getCode()));

		log.info("Person {} found in database.", personId);

		// Map the entity to model object and generate response
		return CommonUtil.generateSuccessResponse(mapper.mapToDto(personEntity));
	}

	/**
	 * Delete a Person by a given Id.
	 * Note:This one is doing hard delete from DB, other option is to soft delete by using a column to mark the Person as Inactive in DB.
	 *
	 * @param personId Id of the person
	 *
	 * @return Empty response
	 */
	@Override
	public Response<Void> deletePerson(String personId) throws PersonException {

		// Get Person from database for the given id, throw exception if not found
		PersonEntity personEntity = personRepository.findByUid(personId).orElseThrow(
				() -> new PersonException(PersonExceptionCode.INVALID_PERSON_ID.getCode()));

		log.info("Person with id {} found in DB.", personId);

		personRepository.delete(personEntity);

		log.info("Person with id {} deleted from database.", personId);

		// Generate success response
		return CommonUtil.generateSuccessResponse();
	}

	/**
	 * Get Person list from database
	 *
	 * @return Response having Person list
	 */
	@Override
	public Response<List<Person>> getPersonList() throws PersonException {

		log.info("Get Person list from database");

		return CommonUtil.generateSuccessResponse(mapper.mapToDto(this.personRepository.findAll()));
	}
}
