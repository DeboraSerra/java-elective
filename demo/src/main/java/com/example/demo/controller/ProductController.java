package com.example.demo.controller;

import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  private ProductService service;

  @Autowired
  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public String getMessage() {
    return service.getMessage();
  }
}
