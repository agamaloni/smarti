package com.challenge.smarti.entities;

import com.challenge.smarti.validators.ValidInterfaceType;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPOJOBuilder
public class PersonBaseEntity {
    //We can make a base object regard this field for dynamic objects solution
    @NotBlank
    private String entityType;

    @NotBlank
    @ValidInterfaceType
    private InterfaceEnum typeOfInterface;

    @NotBlank
    private String tz;

    @NotBlank
    private String name;

    @Min(0)
    @Max(130)
    private int age;

    @Valid
    private Address address;

}
