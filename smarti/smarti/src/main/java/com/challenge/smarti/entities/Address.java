package com.challenge.smarti.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address {
    @NotBlank
    private String city;

    @NotBlank
    private String region;

}
