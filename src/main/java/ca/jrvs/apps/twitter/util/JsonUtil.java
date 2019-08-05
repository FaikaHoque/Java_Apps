package ca.jrvs.apps.twitter.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtil {

    /**
     * Convert a java object to JSON string
     * @param object input
     * @return JSON String
     * throws JsonProcessingException
     */

    public static String toPrettyJson(Object object) throws JsonProcessingException{
        return toJson(object, true, false);
    }
    public static String toJson(Object object, boolean prettyJson, boolean includeNullValues) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();

        if(!includeNullValues)
        {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        if(prettyJson)
        {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return objectMapper.writeValueAsString(object);
    }
    /**
     * Parse JSON string to a object
     * @param json JSON str
     * @param clazz object class
     * @param <T> Type
     * @return Object
     * @throws java.io.IOException
     */
    public static <T> T toObjectFromJson(String json, Class clazz) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) objectMapper.readValue(json, clazz);
    }

}
