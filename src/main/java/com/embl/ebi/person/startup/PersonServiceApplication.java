package com.embl.ebi.person.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Start up application class
 */
@SpringBootApplication(scanBasePackages = "com.embl.ebi.person")
@EnableJpaRepositories(basePackages = "com.embl.ebi.person.repository")
@EntityScan(basePackages = "com.embl.ebi.person.entity")
public class PersonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonServiceApplication.class, args);
	}

}
