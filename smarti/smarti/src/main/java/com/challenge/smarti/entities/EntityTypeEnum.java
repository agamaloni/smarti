package com.challenge.smarti.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EntityTypeEnum {
    @JsonProperty("person")
    PERSON,
    @JsonProperty("car")
    CAR;
}
