package com.example.mylab;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MylabApplicationTests {

	private final ApplicationContext context;

	MylabApplicationTests(ApplicationContext context) {
		this.context = context;
	}

	@Test
	void contextLoads() {
		assertNotNull(context);
	}

}