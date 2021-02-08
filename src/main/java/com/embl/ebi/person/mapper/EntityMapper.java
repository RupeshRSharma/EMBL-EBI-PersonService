package com.embl.ebi.person.mapper;

import com.embl.ebi.person.exception.PersonException;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * The interface for Entity mapper.
 * @param <U>
 *          the type parameter
 * @param <V>
 *          the type parameter
 */
public interface EntityMapper<U, V> {

  /**
   * This method takes the Entity object and maps it to the model class object.
   * @param entity-
   *          Entity Object
   * @return V- Model class Object
   */
  V mapToDto(@NotNull U entity) throws PersonException;

  /**
   * This method takes the Entity object and maps it to the model class object.
   * @param entities-
   *          List of Entity Object
   * @return List V- List of Model class Object
   */
  List<V> mapToDto(@NotNull List<U> entities);

  /**
   * This method takes the Model object and maps it to the Entity class object.
   * @param model-
   *          model Object
   * @return V- Model class Object
   */
  U mapToEntity(V model);

  /**
   * This method takes the model object and maps it to the entity class object.
   * @param models-
   *          List of model Object
   * @return List V- List of entity class Object
   */
  List<U> mapToEntity(@NotNull List<V> models);

}
