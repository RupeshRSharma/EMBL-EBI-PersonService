package com.embl.ebi.person.repository;

import com.embl.ebi.person.entity.PersonEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for Person entity
 */
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {

	/**
	 * Find Person by UID
	 * @param personUid
	 *          Unique id of the Person
	 * @return Person entity requested wrapped in Optional
	 */
	Optional<PersonEntity> findByUid(String personUid);
}
