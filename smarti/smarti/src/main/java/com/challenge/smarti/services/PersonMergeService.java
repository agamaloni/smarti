package com.challenge.smarti.services;

import com.challenge.smarti.PersonPrioritiesConfig;
import com.challenge.smarti.entities.InterfaceEnum;
import com.challenge.smarti.entities.PersonBaseEntity;
import com.challenge.smarti.mapper.JsonToPersonsMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

@AllArgsConstructor
@Getter
@Service
public class PersonMergeService {

    private PersonPrioritiesConfig personPrioritiesConfig;
    private JsonToPersonsMapper jsonToPersonsMapper;

    public PersonBaseEntity mergeEntities(List<PersonBaseEntity> entities) {
        if(entities.size() != 2) throw new IllegalArgumentException("Expected 2 interfaces of type PersonBaseEntity");

        // Initialize the merged entity with the first nd second entity in the list
        PersonBaseEntity C2Person = null;
        PersonBaseEntity webintPerson = null;
        if(entities.get(0).getTypeOfInterface().equals(InterfaceEnum.C2)) {
            C2Person = entities.get(0);
            webintPerson = entities.get(1);
        }else {
            C2Person = entities.get(1);
            webintPerson = entities.get(0);
        }

        Map<String, List<String>> personInterfaceTypePriorities = personPrioritiesConfig.getPersonInterfaceTypePriorities();
        PersonBaseEntity mergedPersonByPriority = new PersonBaseEntity();

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

    private void mergeFieldWithPriorityStrings(Supplier<String> C2Person, Supplier<String> webintPerson, Consumer<String> mergedValueConsumer, String field, Map<String, List<String>> personInterfaceTypePriorities) {
        List<String> priorityOrder = personInterfaceTypePriorities.get(field);

        // TODO:should we fix the case there is no priority
        if (priorityOrder.indexOf("c2") > priorityOrder.indexOf("webint")) {
            mergedValueConsumer.accept(webintPerson.get());
        } else {
            mergedValueConsumer.accept(C2Person.get());
        }
    }

    private void mergeFieldWithPriorityInts(IntSupplier C2Person, IntSupplier webintPerson, IntConsumer mergedValueConsumer, String field, Map<String, List<String>> personInterfaceTypePriorities) {
        List<String> priorityOrder = personInterfaceTypePriorities.get(field);

        // TODO:should we fix the case there is no priority
        if (priorityOrder.indexOf("c2") > priorityOrder.indexOf("webint")) {
            mergedValueConsumer.accept(webintPerson.getAsInt());
        } else {
            mergedValueConsumer.accept(C2Person.getAsInt());
        }
    }
}
