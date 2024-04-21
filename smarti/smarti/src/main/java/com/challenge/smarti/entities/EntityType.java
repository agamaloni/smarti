package com.challenge.smarti.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EntityType {
    @JsonProperty("person")
    PERSON,
    @JsonProperty("car")
    CAR;
}
