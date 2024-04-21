package com.challenge.smarti.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import static com.challenge.smarti.mapper.ConstantsHelper.*;

@Component
public class JsonToPersonsMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JSONArray processJsonStringToListJson(String jsonString) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonString);

        if (jsonArray.length()!=2) throw new IllegalArgumentException(INVALID_JSON_EXPECTED_2_JSON_TO_MERGE);
        if (!jsonArray.getJSONObject(0).get(ENTITY_TYPE).equals(jsonArray.getJSONObject(1).get(ENTITY_TYPE)))
            throw new IllegalArgumentException(EXPECTED_TO_MERGE_ONLY_SAME_ENTITY_TYPE);
        if (jsonArray.getJSONObject(0).get(INTERFACE_TYPE).equals(jsonArray.getJSONObject(1).get(INTERFACE_TYPE)))
            throw new IllegalArgumentException(JSON_EXPECTED_2_DIFFERENT_INTERFACES);

        return jsonArray;
    }
}
