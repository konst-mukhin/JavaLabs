package com.example.lab2vscode.controller;

import com.example.lab2vscode.service.CounterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/counter")
@RestController
@AllArgsConstructor
public class CounterController {

  private CounterService counterService;

  @GetMapping
  public int getCounter() {
    return counterService.get();
  }
}
