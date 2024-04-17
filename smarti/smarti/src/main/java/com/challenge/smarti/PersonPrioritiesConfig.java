package com.challenge.smarti;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@Configuration
@ConfigurationProperties
public class PersonPrioritiesConfig {

        private String entityType;
        private Map<String, List<String>> priorities;
}