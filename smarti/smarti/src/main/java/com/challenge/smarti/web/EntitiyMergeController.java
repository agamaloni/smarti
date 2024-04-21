package com.challenge.smarti.web;

import com.challenge.smarti.services.EntityMergeService;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/person")
public class EntitiyMergeController {
    private EntityMergeService entityMergeService;
    @PostMapping("/merge")
    public ResponseEntity<String> mergeEntities(@RequestBody String jsonString) throws JSONException {
        JSONArray listOfEntities = entityMergeService.getJsonToPersonsMapper().processJsonStringToListJson(jsonString);
        JSONObject mergedEntity = entityMergeService.mergeEntities(listOfEntities,"attributes");
        return ResponseEntity.ok(mergedEntity.toString());
    }
}
