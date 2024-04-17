package com.challenge.smarti.web;

import com.challenge.smarti.entities.PersonBaseEntity;
import com.challenge.smarti.services.PersonMergeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/person")
public class PersonMergeController {

    private PersonMergeService personMergeService;

    @PostMapping("/merge")
    public ResponseEntity<PersonBaseEntity> mergeEntities(@RequestBody @Valid String jsonString) {
        List<PersonBaseEntity> mergedEntity = personMergeService.getJsonToPersonsMapper().processJsonToPersonList(jsonString);
        return ResponseEntity.ok(personMergeService.mergeEntities(mergedEntity));
    }
}
