package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  public String getMessage() {
    return "Message: Product returned";
  }
}
