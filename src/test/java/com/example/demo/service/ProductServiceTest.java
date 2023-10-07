package com.example.demo.service;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring_lesson_one.com.example.demo.DemoApplication;
import spring_lesson_one.com.example.demo.exception.ObjectNotFoundException;
import spring_lesson_one.com.example.demo.jpa.Product;
import spring_lesson_one.com.example.demo.jpa.ProductRepository;
import spring_lesson_one.com.example.demo.service.DefaultProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = DemoApplication.class)
public class ProductServiceTest implements WithAssertions {
  @Autowired
  DefaultProductService productService;

  @BeforeEach
  public void setup() {
    ProductRepository productRepository = new ProductRepository(new ArrayList<>());
    productService = new DefaultProductService(productRepository);
  }

  @Test
  public void findAll() {
    var expected = List.of(productService.create(new Product(1L, "Product One", new BigDecimal(10))), productService.create(new Product(2L, "Product Two", new BigDecimal(11))));
    var result = productService.findAll();
    assertEquals(expected, result);
  }

  @Test
  public void findById() {
    var product = productService.create(new Product(1L, "Product one", new BigDecimal(20)));
    assertEquals(product, productService.findById(product.getId()));
  }

  @Test
  public void create() {
    var product = new Product(1L, "Product one", new BigDecimal(20));
    var result = productService.create(product);
    assertEquals(product, result);
  }

  @Test
  public void delete() {
    var productOne = productService.create(new Product(1L, "Product one", new BigDecimal(20)));
    productService.delete(productOne.getId());
    assertThrows(ObjectNotFoundException.class, () -> productService.findById(productOne.getId()));
  }
}
