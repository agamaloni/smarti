package com.challenge.smarti.mapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonToPersonsMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JSONArray processJsonStringToListJson(String jsonString) throws JSONException {
        JSONArray array = new JSONArray(jsonString);

        if (array.length()!=2) throw new IllegalArgumentException("Invalid JSON - expected 2 JSON to merge");
        if (!array.getJSONObject(0).get("entityType").equals(array.getJSONObject(1).get("entityType")))
            throw new IllegalArgumentException("Invalid JSON - expected to merge only same entityType ");
        if (array.getJSONObject(0).get("interfaceType").equals(array.getJSONObject(1).get("interfaceType")))
            throw new IllegalArgumentException("Invalid JSON - expected 2 different interfaces");

        /*List<BaseEntity> entities = new LinkedList<>();
        try {
            entities = objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

        return array;
    }
}
