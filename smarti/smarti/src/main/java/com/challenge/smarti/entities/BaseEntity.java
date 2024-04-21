package com.challenge.smarti.entities;

import com.challenge.smarti.validators.ValidInterfaceType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {
    //We can make a base object regard this field for dynamic objects solution
    @NotBlank
    private String entityType;

    @NotBlank
    @ValidInterfaceType
    private InterfaceEnum interfaceType;

    private List<Attribute> attributes;

}
