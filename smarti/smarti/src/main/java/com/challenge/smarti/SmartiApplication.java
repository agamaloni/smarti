package com.challenge.smarti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartiApplication implements CommandLineRunner {

	@Autowired
	private EntitiesPrioritiesConfig entitiesPrioritiesConfig;

	public static void main(String[] args) {
		SpringApplication.run(SmartiApplication.class, args);
	}

	//for dev test propose only - loading context
	public void run(String... args) throws Exception {
		System.out.println("Load entities config: " + entitiesPrioritiesConfig.getEntities().size());
	}

}
