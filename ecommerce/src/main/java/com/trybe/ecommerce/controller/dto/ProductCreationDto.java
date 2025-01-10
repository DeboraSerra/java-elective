package com.trybe.ecommerce.controller.dto;

import com.trybe.ecommerce.entities.Product;

public record ProductCreationDto(String name, Double price) {

  public Product toEntity() {
    return new Product(name, price);
  }
}
