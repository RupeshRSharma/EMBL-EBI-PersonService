package com.embl.ebi.person.mapper;

import com.embl.ebi.person.entity.PersonEntity;
import com.embl.ebi.person.model.Person;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Person mapper.
 */
@Component("personMapper")
public class PersonMapper extends ViewMapper implements EntityMapper<PersonEntity, Person> {

  /**
   * This method takes the Entity object and maps it to the model class object.
   * @param personEntity
   *          - Entity Object
   * @return Person- Model class Object
   */
  @Override
  public Person mapToDto(@NotNull PersonEntity personEntity) {
    return this.map(personEntity, Person.class);
  }

  /**
   * This method takes the Entity object and maps it to the model class object.
   * @param personEntities
   *          - List of Entity Object
   * @return List of Persons - List of Model class Object
   */
  @Override
  public List<Person> mapToDto(@NotNull List<PersonEntity> personEntities) {
    return personEntities.stream().map(this::mapToDto).collect(Collectors.toList());

  }

  /**
   * This method takes the Model object and maps it to the Entity class object.
   * @param person
   *          - model Object
   * @return PersonEntity - Model class Object
   */
  @Override
  public PersonEntity mapToEntity(Person person) {
    return this.map(person, PersonEntity.class);
  }

  /**
   * This method takes the model object and maps it to the entity class object.
   * @param persons
   *          - List of model Object
   * @return List PersonEntity- List of entity class Object
   */
  @Override
  public List<PersonEntity> mapToEntity(@NotNull List<Person> persons) {
    return persons.stream().map(this::mapToEntity).collect(Collectors.toList());
  }

}
