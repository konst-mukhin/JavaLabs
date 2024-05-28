package com.example.lab2vscode;


import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Lab2vscodeApplicationTests {

  private final ApplicationContext context;

  Lab2vscodeApplicationTests(ApplicationContext context) {
    this.context = context;
  }

  // @Test
  // void contextLoads() {
  //   assertNotNull(context);
  // }
}
