package com.challenge.smarti.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class Attribute {
    private String name;
    private List<String> interfaces;
    private boolean hasNested ;
    private List<Attribute> nestedAttributes;
}
