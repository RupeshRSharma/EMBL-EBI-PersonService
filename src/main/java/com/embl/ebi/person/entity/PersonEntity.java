package com.embl.ebi.person.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * JPA entity for Persons table to store person related information.
 */
@Entity
@Table(name = "persons")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Data
public class PersonEntity {

	// Primary key Id
	@Id
	@Column(updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Unique id of the payment to be shared with external world
	 * as database generated primary key ids should not shared for multiple reasons
	 * like resource harvesting or database migration or disaster recovery
	 */
	@Column(unique = true, nullable = false, updatable = false)
	public String uid;

	// First name of the person
	@Column(nullable = false)
	private String first_name;

	// Last name of the person
	@Column(nullable = false)
	private String last_name;

	// Age of the person
	@Column(nullable = false)
	private int age;

	// Favourite color of the person
	@Column(nullable = false)
	private String favourite_color;

}
