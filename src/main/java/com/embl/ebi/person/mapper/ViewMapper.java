package com.embl.ebi.person.mapper;

import com.embl.ebi.person.exception.PersonException;
import com.embl.ebi.person.exception.PersonExceptionCode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.lang.reflect.Field;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * The type View mapper.
 */
@Component
@Log4j2
public class ViewMapper {

  private ObjectMapper mapper = serializingObjectMapper();

  /**
   * Map t.
   * @param <T>
   *          the type parameter
   * @param obj
   *          the obj
   * @param clazz
   *          the clazz
   * @return the t
   */
  public <T extends Object> T map(Object obj, Class<T> clazz) {
    mapper = mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.convertValue(obj, clazz);
  }

  /**
   * Map t.
   * @param <T>
   *          the type parameter
   * @param obj
   *          the obj
   * @param updateMap
   *          the update map
   * @return the t
   * @throws PersonException
   *           the exception
   */
  @SuppressWarnings("unchecked")
  public <T extends Object> T map(Object obj, Map<String, String> updateMap) throws PersonException {
    try {
      for (Map.Entry<String, String> entry : updateMap.entrySet()) {
        Field field = obj.getClass().getDeclaredField(entry.getKey());
        field.setAccessible(true);
        Class<?> type = field.getType();
        Object extvalue = field.get(obj);
        log.info("existing value {}, new value {}, type {}", getString(extvalue),
            getString(entry.getValue()), type.toString());
        if (type.equals(String.class)) {
          field.set(obj, entry.getValue());
        } else if (type.equals(Long.class) || type.toString().equals("long")) {
          field.setLong(obj, Long.valueOf(entry.getValue()));
        } else if (type.equals(Boolean.class) || type.toString().equals("boolean")) {
          field.setBoolean(obj, Boolean.valueOf(entry.getValue()));
        } else if (type.equals(Double.class) || type.toString().equals("double")) {
          field.setDouble(obj, Double.valueOf(entry.getValue()));
        } else if (type.equals(Float.class) || type.toString().equals("float")) {
          field.setFloat(obj, Float.valueOf(entry.getValue()));
        } else if (type.equals(Integer.class) || type.toString().equals("int")) {
          field.setInt(obj, Integer.valueOf(entry.getValue()));
        }
        field.setAccessible(false);
      }
    } catch (IllegalAccessException e) {
      log.error("Failed to access field", e);
      throw new PersonException(PersonExceptionCode.INVALID_INPUT.getCode(), e);
    } catch (NoSuchFieldException e) {
      log.error("Failed to determine field", e);
      throw new PersonException(PersonExceptionCode.INVALID_INPUT.getCode(), "No Such field- " + e.getMessage());
    }
    return (T) obj;
  }

  private String getString(Object obj) {
    if (obj == null) {
      return null;
    }
    return obj.toString();
  }

  /**
   * Serializing object mapper object mapper.
   * @return the object mapper
   */
  public ObjectMapper serializingObjectMapper() {

    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule();

    objectMapper.registerModule(simpleModule);
    return objectMapper;
  }

}
