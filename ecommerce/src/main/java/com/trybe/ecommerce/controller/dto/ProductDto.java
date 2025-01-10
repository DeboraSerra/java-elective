package com.trybe.ecommerce.controller.dto;

import com.trybe.ecommerce.entities.Product;

public record ProductDto(Long id, String name, Double price) {

  public static ProductDto fromEntity(Product product) {
    return new ProductDto(product.getId(), product.getName(), product.getPrice());
  }
}
