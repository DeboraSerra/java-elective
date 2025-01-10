package com.trybe.ecommerce.service;


import com.trybe.ecommerce.entities.Product;
import com.trybe.ecommerce.repository.ProductRepository;
import com.trybe.ecommerce.service.exception.ProductNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findProducts() {
    return productRepository.findAll();
  }

  public Product findById(Long id) {
    return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
  }

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public Product update(Product product, Long id) {
    Product found = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    found.setName(product.getName());
    found.setPrice(product.getPrice());
    return productRepository.save(found);
  }

  public Product delete(Long id) {
    Product found = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    productRepository.deleteById(id);
    return found;
  }
}
