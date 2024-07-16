package com.courseoracle.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> class_) {
        try {
            return objectMapper.readValue(json, class_);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
