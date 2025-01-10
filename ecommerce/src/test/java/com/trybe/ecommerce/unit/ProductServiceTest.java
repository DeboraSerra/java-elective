package com.trybe.ecommerce.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.trybe.ecommerce.entities.Product;
import com.trybe.ecommerce.repository.ProductRepository;
import com.trybe.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

  @Autowired
  ProductService productService;

  @MockitoBean
  ProductRepository productRepository;

  @Test
  public void testProductCreation() {
    Product product = new Product();
    product.setName("Frying Pan");
    product.setPrice(20D);

    Product mockedProd = new Product();
    product.setPrice(20D);
    product.setName("Frying Pan");
    product.setId(1L);

    Mockito.when(productRepository.save(product)).thenReturn(mockedProd);

    Product saved = productService.save(product);
    Mockito.verify(productRepository).save(product);
    assertEquals(saved.getId(), mockedProd.getId());
    assertEquals(saved.getName(), mockedProd.getName());
    assertEquals(saved.getPrice(), mockedProd.getPrice());
  }
}
