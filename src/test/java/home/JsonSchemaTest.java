package home;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import org.junit.jupiter.api.Test;

class JsonSchemaTest {
  @Test
  void test() throws Exception {
    var objectMapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
    System.out.println(
        objectMapper.writeValueAsString(
            new JsonSchemaGenerator(objectMapper).generateSchema(Action.class)));
  }
}
