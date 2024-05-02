package com.example.lab2vscode;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Lab2vscodeApplicationTests {

  private final ApplicationContext context;

  Lab2vscodeApplicationTests(ApplicationContext context) {
    this.context = context;
  }

  @Test
  void contextLoads() {
    assertNotNull(context);
  }
}
