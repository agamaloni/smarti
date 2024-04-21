package com.challenge.smarti.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EntityConfig {

    private String entityType;
    private List<Attribute> attributes;


}
