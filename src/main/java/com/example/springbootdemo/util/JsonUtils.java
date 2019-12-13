package com.example.springbootdemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtils {
    public static String object2Json(Object obj){
        String result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Map<?,?> object2Map(Object obj) {
        String object2Json = object2Json(obj);
        Map<?, ?> result = jsonToMap(object2Json);
        return result;
    }

    public static Map<?, ?> jsonToMap(String json) {
        return json2Object(json, Map.class);
    }

    public static <T> T json2Object(String json, Class<T> cls) {
        T result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static <T> T conveterObject(Object srcObject, Class<T> destObjectType) {
        String jsonContent = object2Json(srcObject);
        return json2Object(jsonContent, destObjectType);
    }
}
