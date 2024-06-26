package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Controller {

  @GetMapping
  public String sayHello(String name) {
    if (!name.isEmpty()) {
      return "Hello, %s".formatted(name);
    }
    return "Hello World!";
  }
}
