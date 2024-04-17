package com.challenge.smarti;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration
@PropertySource(value = "classpath:static/person-priorities.yaml")
@ConfigurationProperties(prefix = "person-priorities")
public class PersonPrioritiesConfig {

    private Map<String, List<String>> personInterfaceTypePriorities;


}