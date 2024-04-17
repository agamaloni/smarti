package com.challenge.smarti;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class SmartiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}


	@Test
	void testMergeEntitiesPersonSuccess() throws Exception {
		String incomingJsonString =
				"[\n" +
						"    {\n" +
						"        \"entityType\": \"person\",\n" +
						"        \"typeOfInterface\": \"c2\",\n" +
						"        \"tz\": \"GMT\",\n" +
						"        \"name\": \"Jerry Seinfeld\",\n" +
						"        \"age\": \"69\",\n" +
						"        \"address\": {\n" +
						"            \"city\": \"New York City\",\n" +
						"            \"region\": \"New York\"\n" +
						"        }\n" +
						"    },\n" +
						"    {\n" +
						"        \"entityType\": \"person\",\n" +
						"        \"typeOfInterface\": \"webint\",\n" +
						"        \"tz\": \"GMT\",\n" +
						"        \"name\": \"Julia Louis-Dreyfus\",\n" +
						"        \"age\": \"63\",\n" +
						"        \"address\": {\n" +
						"            \"city\": \"Manhattan\",\n" +
						"            \"region\": \"New York\"\n" +
						"        }\n" +
						"    }\n" +
						"]";


		String expectedJsonAfterMerged = "{\n" +
				"    \"entityType\": null,\n" +
				"    \"typeOfInterface\": null,\n" +
				"    \"tz\": \"GMT\",\n" +
				"    \"name\": \"Julia Louis-Dreyfus\",\n" +
				"    \"age\": 69,\n" +
				"    \"address\": {\n" +
				"        \"city\": \"New York City\",\n" +
				"        \"region\": \"New York\"\n" +
				"    }\n" +
				"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/api/person/merge")
						.contentType(MediaType.APPLICATION_JSON)
						.content(incomingJsonString))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(expectedJsonAfterMerged));
	}

}
