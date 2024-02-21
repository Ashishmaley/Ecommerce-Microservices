package com.e.website;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import com.e.website.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class WebsiteApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper oM;

	@Container
	public static final GenericContainer<?> mongoContainer = new GenericContainer<>("mongo:latest")
			.withExposedPorts(27017);

	@BeforeEach
	void setup() {
		mongoContainer.start();

		String host = mongoContainer.getHost();
		Integer port = mongoContainer.getMappedPort(27017);
		String mongoDbConnectionUrl = "mongodb://" + host + ":" + port;
		System.setProperty("spring.data.mongodb.uri", mongoDbConnectionUrl);
	}

	@AfterAll
	static void cleanup() {
		mongoContainer.stop();
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest p = getProduct();
		String s = oM.writeValueAsString(p);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product").contentType("application/json")
				.content(s)).andExpect(status().isCreated());
	}

	private ProductRequest getProduct() {
		return ProductRequest.builder().name("maggi").desc("lol").price(BigDecimal.valueOf(9.9)).build();
	}
}
