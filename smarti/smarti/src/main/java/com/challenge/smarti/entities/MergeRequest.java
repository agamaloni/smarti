package com.challenge.smarti.entities;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonPOJOBuilder
public class MergeRequest {

    private List<PersonBaseEntity> entities;

    @Builder
    public MergeRequest(List<PersonBaseEntity> entities) {
        this.entities = entities;
    }



}
