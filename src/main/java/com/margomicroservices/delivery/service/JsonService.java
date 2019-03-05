package com.margomicroservices.delivery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonService {
    private ObjectMapper objectMapper;

    @Autowired
    public JsonService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> String  toJsonString(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
