package com.zendesk.ticketviewer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonUtils {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static String toJson(Object object){
        try {
            return JSON_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("write to json string error:" + object, e);
            return null;
        }
    }

    public static <T> T toBean(String json, Class<T> clazz){
        if(StringUtils.isEmpty(json)){
            return null;
        }else{
            try {
                return JSON_MAPPER.readValue(json, clazz);
            } catch (JsonProcessingException e) {
                log.error("parse json string error:" + json, e);
                return null;
            }
        }
    }

    public static <T> List<T> fromJson(String json, Class<T> clazz){
        if(StringUtils.isEmpty(json)){
            return null;
        }
        JavaType javaType = JSON_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            List<T> result = JSON_MAPPER.readValue(json, javaType);
            return result;
        } catch (JsonProcessingException e) {
            log.error("parse json string error:" + json, e);
            return null;
        }
    }

    public static JsonNode toJsonNode(String json){
        if(StringUtils.isEmpty(json)){
            return null;
        }
        try {
            return JSON_MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("write to json node error:" + json, e);
            return null;
        }
    }
}
