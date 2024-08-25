package com.aluracurse.challengebooks.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterData implements IConverterData{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class <T> class_){
        try{
            return mapper.readValue(json, class_);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }



}
