package com.trybe.ecommerce.controller;

import com.trybe.ecommerce.controller.dto.ProductCreationDto;
import com.trybe.ecommerce.controller.dto.ProductDto;
import com.trybe.ecommerce.entities.Product;
import com.trybe.ecommerce.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<ProductDto> save(@RequestBody ProductCreationDto body) {
    Product product = body.toEntity();
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ProductDto.fromEntity(productService.save(product)));
  }

  @GetMapping
  public ResponseEntity<List<ProductDto>> findAllProducts() {
    List<Product> products = productService.findProducts();
    List<ProductDto> formattedProducts = products.stream().map(ProductDto::fromEntity).toList();
    return ResponseEntity.status(HttpStatus.OK).body(formattedProducts);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
    Product product = productService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(ProductDto.fromEntity(product));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> update(@RequestBody ProductCreationDto body,
      @PathVariable Long id) {
    Product product = body.toEntity();
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(ProductDto.fromEntity(productService.update(product, id)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ProductDto> deleteById(@PathVariable Long id) {
    Product deleted = productService.delete(id);
    ProductDto product = ProductDto.fromEntity(deleted);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
  }
}
