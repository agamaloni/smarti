package com.challenge.smarti;

import com.challenge.smarti.entities.EntityConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties
public class EntitiesPrioritiesConfig {
        private List<EntityConfig> entities;
}