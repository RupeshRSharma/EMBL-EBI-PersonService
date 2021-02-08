package com.embl.ebi.person.controller;

import com.embl.ebi.person.constant.StatusEnum;
import com.embl.ebi.person.model.Person;
import com.embl.ebi.person.response.Response;
import com.embl.ebi.person.service.PersonService;
import com.embl.ebi.person.util.CommonUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * test class for Person controller
 */
public class PersonControllerTest {

	// PERSONS URI
	private static final String PERSONS_URI = "/1.0/persons";

	@Mock // Mock Person service
	private PersonService personService;

	// Mock mvc
	private MockMvc mvc;

	// Object mapper
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Initialize setup before tests.
	 */
	@BeforeClass
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		final PersonController controller = new PersonController(personService);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * Test create Person
	 * @throws Exception MVC exception
	 */
	@Test
	public void createPerson() throws Exception {

		Person person = Person.builder().first_name("FirstName").last_name("LastName").age(10).favourite_color("blue").build();

		// Mock service call
		Mockito.when(personService.createPerson(ArgumentMatchers.any(Person.class))).thenReturn(CommonUtil.generateSuccessResponse(person));

		// Execute the request
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(PERSONS_URI).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(person))).andReturn();

		// getting response body as JSON
		Response response = mapFromJson(mvcResult.getResponse().getContentAsString(), Response.class);

		// assertions on response
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
		Assertions.assertTrue(response.getStatus().equals(StatusEnum.SUCCESS));

	}

	/**
	 * Test update Person
	 * @throws Exception MVC exception
	 */
	@Test
	public void updatePerson() throws Exception {

		// Mock service call
		Mockito.when(personService.updatePerson(ArgumentMatchers.anyString(), ArgumentMatchers.any(Map.class))).thenReturn(CommonUtil.generateSuccessResponse());

		Map<String, String> propMap = new HashMap<>();
		propMap.put("first_name", "LastName");

		// Execute the request
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(PERSONS_URI+"/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(propMap))).andReturn();

		// getting response body as JSON
		Response response = mapFromJson(mvcResult.getResponse().getContentAsString(), Response.class);

		// assertions on response
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
		Assertions.assertTrue(response.getStatus().equals(StatusEnum.SUCCESS));

	}

	/**
	 * Test get Person
	 * @throws Exception MVC exception
	 */
	@Test
	public void getPerson() throws Exception {

		Person person = Person.builder().first_name("FirstName").last_name("LastName").age(10).favourite_color("blue").build();

		// Mock service call
		Mockito.when(personService.getPerson(ArgumentMatchers.anyString())).thenReturn(CommonUtil.generateSuccessResponse(person));

		// Execute the request
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(PERSONS_URI+"/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		// getting response body as JSON
		Response response = mapFromJson(mvcResult.getResponse().getContentAsString(), Response.class);

		// assertions on response
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
		Assertions.assertTrue(response.getStatus().equals(StatusEnum.SUCCESS));

	}

	/**
	 * Test delete Person
	 * @throws Exception MVC exception
	 */
	@Test
	public void deletePerson() throws Exception {

		// Mock service call
		Mockito.when(personService.deletePerson(ArgumentMatchers.anyString())).thenReturn(CommonUtil.generateSuccessResponse());

		// Execute the request
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(PERSONS_URI+"/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		// getting response body as JSON
		Response response = mapFromJson(mvcResult.getResponse().getContentAsString(), Response.class);

		// assertions on response
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
		Assertions.assertTrue(response.getStatus().equals(StatusEnum.SUCCESS));

	}

	/**
	 * Map from json t.
	 * @param <T>
	 *          the type parameter
	 * @param json
	 *          the json
	 * @param clazz
	 *          the clazz
	 * @return the t
	 * @throws JsonMappingException
	 *           the json mapping exception
	 * @throws IOException
	 *           the io exception
	 */
	private  <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

}
