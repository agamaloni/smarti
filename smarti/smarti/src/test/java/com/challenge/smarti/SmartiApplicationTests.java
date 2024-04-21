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
						"        \"interfaceType\": \"c2\",\n" +
						"        \"attributes\": {\n" +
						"            \"tz\": \"c2\",\n" +
						"            \"name\": \"c2\",\n" +
						"            \"age\": \"c2\",\n" +
						"            \"address\": {\n" +
						"                \"city\": \"c2\",\n" +
						"                \"region\": \"c2\"\n" +
						"            }\n" +
						"        }\n" +
						"    },\n" +
						"    {\n" +
						"        \"entityType\": \"person\",\n" +
						"        \"interfaceType\": \"webint\",\n" +
						"        \"attributes\": {\n" +
						"            \"tz\": \"webint\",\n" +
						"            \"name\": \"webint\",\n" +
						"            \"age\": \"webint\",\n" +
						"            \"address\": {\n" +
						"                \"city\": \"webint\",\n" +
						"                \"region\": \"webint\"\n" +
						"            }\n" +
						"        }\n" +
						"    }\n" +
						"]\n";


		String expectedJsonAfterMerged = "{\"tz\":\"webint\",\"name\":\"webint\",\"age\":\"c2\",\"address\":{\"city\":\"c2\",\"region\":\"webint\"}}";

		mockMvc.perform(MockMvcRequestBuilders.post("/api/person/merge")
						.contentType(MediaType.APPLICATION_JSON)
						.content(incomingJsonString))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(expectedJsonAfterMerged));
	}

}
