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

@AllArgsConstructor
@Getter
@Service
public class EntityMergeService {
    private EntitiesPrioritiesConfig entitiesPrioritiesConfig;
    private JsonToPersonsMapper jsonToPersonsMapper;

    public JSONObject mergeEntities(JSONArray entities, String jsonNode) throws JSONException {
        JSONObject firstEntity = entities.getJSONObject(0);
        JSONObject secondEntity = entities.getJSONObject(1);
        String entityType = firstEntity.getString("entityType");
        JSONObject firstEntityAttributes = firstEntity.getJSONObject(jsonNode);
        JSONObject secondEntityAttributes = secondEntity.getJSONObject(jsonNode);
        JSONObject mergedEntityProperties = new JSONObject();
        List<EntityConfig> fromConfig = entitiesPrioritiesConfig.getEntities();
        List<Attribute> attributes = fromConfig
                .stream()
                .filter(entityConfig -> entityConfig.getEntityType().equals(entityType))
                .findFirst()
                .map(EntityConfig::getAttributes)
                .orElseThrow(() -> new RuntimeException("Entity type not found in configuration"));
        if(!jsonNode.equals("attributes")){
            attributes = attributes.stream().filter(Attribute::isHasNested).findAny().stream().findFirst()
                    .orElseThrow(() -> new RuntimeException("nestedConfigurations not found in configuration")).getNestedAttributes();
        }for (Attribute attribute : attributes) {
            System.out.println("++++++++++++++++++++++++++++++++");
            System.out.println("The attribute to merge is : " + attribute.getName());
            if (attribute.getInterfaces() != null) {
                for (String interfaceType : attribute.getInterfaces()) {
                    if (firstEntity.get("interfaceType").equals(interfaceType)) {
                        String value = secondEntityAttributes.optString(attribute.getName());
                        if (!value.isEmpty()) {
                            mergedEntityProperties.put(attribute.getName(), value);}} else {
                        String value = firstEntityAttributes.optString(attribute.getName());
                        if (!value.isEmpty()) {
                            mergedEntityProperties.put(attribute.getName(), value);}}}} else {
                if (attribute.isHasNested()) {
                    JSONArray nestedEntities = new JSONArray();
                    JSONObject firstEntityNested = new JSONObject();
                    firstEntityNested.put("entityType",firstEntity.get("entityType"));
                    firstEntityNested.put("interfaceType",firstEntity.get("interfaceType"));
                    firstEntityNested.put(attribute.getName(),firstEntityAttributes.getJSONObject(attribute.getName()));
                    JSONObject secondEntityNested = new JSONObject();
                    secondEntityNested.put("entityType",secondEntity.get("entityType"));
                    secondEntityNested.put("interfaceType",secondEntity.get("interfaceType"));
                    secondEntityNested.put(attribute.getName(),secondEntityAttributes.getJSONObject(attribute.getName()));
                    nestedEntities.put(firstEntityNested);
                    nestedEntities.put(secondEntityNested);
                    JSONObject nestedMergedProperties = mergeEntities(nestedEntities, attribute.getName());
                    mergedEntityProperties.put(attribute.getName(), nestedMergedProperties);}}}
        return mergedEntityProperties;}

    private JSONObject getEntityByInterfaceType(List<JSONObject> entities, String interfaceType) {
        return entities.stream()
                .filter(entity -> {
                    try {
                        return entity.get("entityType").equals(interfaceType);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                })
                .findFirst()
                .orElse(null);
    }


/* on hold lets keep it simple for now

    public BaseEntity mergeEntities(List<BaseEntity> entities) throws JSONException {
        validateList(entities);
        String entityType = entities.get(0).getEntityType();

        // TODO: generic init Initialize the merged entity with the first nd second entity in the list
        BaseEntity C2Person = null;
        BaseEntity webintPerson = null;
        if(entities.get(0).getTypeOfInterface().equals(InterfaceEnum.C2)) {
            C2Person = entities.get(0);
            webintPerson = entities.get(1);
        }else {
            C2Person = entities.get(1);
            webintPerson = entities.get(0);
        }

        Map<String, Map<String, List<String>>> entitiesMap = entitiesPrioritiesConfig.getEntitiesMap();
        Map<String,List<String>> priorities = entitiesMap.get(entityType);
        JSONObject jsonObject = new JSONObject();

        String C2Interface = C2Person.getTypeOfInterface().name();
        String webintInterface = webintPerson.getTypeOfInterface().name();

        for (String field: priorities.keySet()) {


            String prioritizedInterface = priorities.get(field).get(0);

            if(C2Person.getProperties().get(field).size() == 1) {
                String resultFieldInterface = C2Interface.equals(prioritizedInterface) ?  C2Person.getProperties().get(field).get(0) : webintPerson.getProperties().get(field).get(0);
                jsonObject.put(field,resultFieldInterface);
            }
            else{


            }

            mergeNew(C2Person.getProperties().get(field), webintPerson.getProperties().get(field), jsonObject);

            priorities.get(field)

        }

        jsonObject.put("person")

        BaseEntity mergedPersonByPriority = createPersonBaseEntity();

        if(C2Person != null && webintPerson != null) {
            // Merge logic based on priorities from YAML configuration
            mergeFieldWithPriorityStrings(C2Person::getTz, webintPerson::getTz, mergedPersonByPriority::setTz, "tz", personInterfaceTypePriorities);
            mergeFieldWithPriorityStrings(C2Person::getName, webintPerson::getName, mergedPersonByPriority::setName, "name", personInterfaceTypePriorities);

            mergeFieldWithPriorityInts(C2Person::getAge, webintPerson::getAge, mergedPersonByPriority::setAge, "age", personInterfaceTypePriorities);

            mergeFieldWithPriorityStrings(C2Person.getAddress()::getCity, webintPerson.getAddress()::getCity, mergedPersonByPriority.getAddress()::setCity, "address.city", personInterfaceTypePriorities);
            mergeFieldWithPriorityStrings(C2Person.getAddress()::getRegion, webintPerson.getAddress()::getRegion, mergedPersonByPriority.getAddress()::setRegion, "address.region", personInterfaceTypePriorities);
        }
        return mergedPersonByPriority;
    }
*/

    private static BaseEntity createPersonBaseEntity() {
        BaseEntity mergedPersonByPriority = new BaseEntity();
        return mergedPersonByPriority;
    }

    private static void validateList(List<BaseEntity> entities) {
        if(entities.size() != 2)
            throw new IllegalArgumentException("Expected 2 interfaces for merge entities");

        if(!entities.get(0).getEntityType().equals(entities.get(1).getEntityType()))
            throw new IllegalArgumentException("Both entities must be from the same Entity type");

        if(entities.get(0).getInterfaceType().equals(entities.get(1).getInterfaceType()))
            throw new IllegalArgumentException("Interface types must be from different channels");
    }

}
