package com.challenge.smarti.mapper;

import com.challenge.smarti.entities.PersonBaseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.LinkedList;
import java.util.List;
@Validated
@Component
public class JsonToPersonsMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<PersonBaseEntity> processJsonToPersonList(@Valid String jsonString) {
        List<PersonBaseEntity> entities = new LinkedList<>();
        try {
            entities = objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
