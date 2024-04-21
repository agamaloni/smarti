package com.challenge.smarti.services;

import com.challenge.smarti.EntitiesPrioritiesConfig;
import com.challenge.smarti.entities.Attribute;
import com.challenge.smarti.entities.BaseEntity;
import com.challenge.smarti.entities.EntityConfig;
import com.challenge.smarti.mapper.JsonToPersonsMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.challenge.smarti.mapper.ConstantsHelper.*;

@AllArgsConstructor
@Getter
@Service
public class EntityMergeService {
    private EntitiesPrioritiesConfig entitiesPrioritiesConfig;
    private JsonToPersonsMapper jsonToPersonsMapper;

    public JSONObject mergeEntities(JSONArray entities, String jsonNode) throws JSONException {
        JSONObject firstEntity = entities.getJSONObject(0);
        JSONObject secondEntity = entities.getJSONObject(1);
        String entityType = firstEntity.getString(ENTITY_TYPE);

        JSONObject firstEntityAttributes = firstEntity.getJSONObject(jsonNode);
        JSONObject secondEntityAttributes = secondEntity.getJSONObject(jsonNode);
        JSONObject mergedEntityProperties = new JSONObject();

        List<EntityConfig> entityConfigList = entitiesPrioritiesConfig.getEntities();
        EntityConfig entityConfig = entityConfigList.stream()
                .filter(config -> config.getEntityType().equals(entityType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_IN_CONFIGURATION));

        List<Attribute> attributes = entityConfig.getAttributes();
        if (!jsonNode.equals(ATTRIBUTES)) {
            attributes = attributes.stream()
                    .filter(Attribute::isHasNested)
                    .findAny()
                    .orElseThrow(() -> new RuntimeException(NESTED_CONFIGURATIONS_NOT_FOUND))
                    .getNestedAttributes();
        }

        for (Attribute attribute : attributes) {
            if (attribute.getInterfaces() != null) {
                for (String interfaceType : attribute.getInterfaces()) {
                    if (firstEntity.getString(INTERFACE_TYPE).equals(interfaceType)) {
                        mergeAttributeValues(attribute.getName(), secondEntityAttributes.optString(attribute.getName()), mergedEntityProperties);
                    } else {
                        mergeAttributeValues(attribute.getName(), firstEntityAttributes.optString(attribute.getName()), mergedEntityProperties);
                    }
                }
            } else if (attribute.isHasNested()) {
                handleNestedAttributes(attribute, firstEntity, secondEntity, firstEntityAttributes, secondEntityAttributes, mergedEntityProperties);
            }
        }
        return mergedEntityProperties;
    }

    private void mergeAttributeValues(String attributeName, String value, JSONObject mergedEntityProperties) throws JSONException {
        if (!value.isEmpty()) {
            mergedEntityProperties.put(attributeName, value);
        }
    }

    private void handleNestedAttributes(Attribute attribute, JSONObject firstEntity, JSONObject secondEntity, JSONObject firstEntityAttributes, JSONObject secondEntityAttributes, JSONObject mergedEntityProperties) throws JSONException {
        JSONArray nestedEntities = new JSONArray();
        JSONObject firstEntityNested = new JSONObject();
        firstEntityNested.put(ENTITY_TYPE, firstEntity.get(ENTITY_TYPE));
        firstEntityNested.put(INTERFACE_TYPE, firstEntity.get(INTERFACE_TYPE));
        firstEntityNested.put(attribute.getName(), firstEntityAttributes.getJSONObject(attribute.getName()));

        JSONObject secondEntityNested = new JSONObject();
        secondEntityNested.put(ENTITY_TYPE, secondEntity.get(ENTITY_TYPE));
        secondEntityNested.put(INTERFACE_TYPE, secondEntity.get(INTERFACE_TYPE));
        secondEntityNested.put(attribute.getName(), secondEntityAttributes.getJSONObject(attribute.getName()));

        nestedEntities.put(firstEntityNested);
        nestedEntities.put(secondEntityNested);

        JSONObject nestedMergedProperties = mergeEntities(nestedEntities, attribute.getName());
        mergedEntityProperties.put(attribute.getName(), nestedMergedProperties);
    }
}
