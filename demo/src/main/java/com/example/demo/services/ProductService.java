package com.example.demo.services;

import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private ProductRepository repository;

  @Autowired
  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  public String getMessage() {
    return repository.getMessage();
  }
}
