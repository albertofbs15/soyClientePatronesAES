package util;


import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.IOException;

public class JacksonJdk8 {

    private static final ObjectMapper mapper;

    public JacksonJdk8() {
    }

    public static ObjectMapper mapper() {
        return mapper;
    }

    public static String json(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T value(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }



    static {
        mapper = (new ObjectMapper()).registerModules(new Module[]{new Jdk8Module(), new JavaTimeModule(), new ParameterNamesModule(Mode.PROPERTIES)}).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
