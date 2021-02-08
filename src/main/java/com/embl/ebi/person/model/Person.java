package com.embl.ebi.person.model;

import com.embl.ebi.person.constant.ColorType;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for Person related information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

	// Unique id of the person
	private String uid;

	// First name of the person
	@NotBlank(message = "Person first name can not be empty.")
	private String first_name;

	// Last name of the person
	@NotBlank(message = "Person last name can not be empty.")
	private String last_name;

	// Age of the person
	@NotNull(message = "Person age can not be empty.")
	@Min(value = 1, message = "Person age should be greater than 0.")
	private Integer age;

	// Favourite color of the person
	@ColorType
	@NotBlank(message = "Person favourite color can not be empty.")
	private String favourite_color;

}

