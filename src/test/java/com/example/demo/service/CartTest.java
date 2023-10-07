package com.example.demo.service;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring_lesson_one.com.example.demo.DemoApplication;
import spring_lesson_one.com.example.demo.exception.NegativeNumberException;
import spring_lesson_one.com.example.demo.exception.ObjectNotFoundException;
import spring_lesson_one.com.example.demo.jpa.Product;
import spring_lesson_one.com.example.demo.service.Cart;
import spring_lesson_one.com.example.demo.service.ProductService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoApplication.class)
public class CartTest implements WithAssertions {
  @Autowired
  Cart cart;
  @Autowired
  ProductService productService;

  @BeforeEach
  public void setup() {
    cart.getBasket().clear();
  }

  @Test
  public void addProduct() {
    long productId = 1;
    int quantity = 1;
    var product = productService.create(new Product(productId, "Product one", BigDecimal.valueOf(10)));
    var addedProduct = cart.addProduct(productId, quantity);
    assertEquals(product, addedProduct);
    assertEquals(quantity, cart.getBasket().get(product));
  }

  @Test
  public void delProduct() {
    long productId = 1;
    int quantity = 1;
    var product = productService.create(new Product(productId, "Product one", BigDecimal.valueOf(10)));
    cart.deleteProduct(productId, quantity);
    assertFalse(cart.getBasket().containsKey(product));
  }

  @Test
  public void delProductWithGreeterQuantity() {
    long productId = 1;
    int quantity = 2;
    int greeterQuantity = 3;
    productService.create(new Product(productId, "Product one", BigDecimal.valueOf(100)));
    cart.addProduct(productId, quantity);
    assertThrows(ObjectNotFoundException.class, () -> cart.deleteProduct(quantity, greeterQuantity));
  }

  @Test
  public void addProductNotFound() {
    long productId = 1;
    int quantity = 1;
    assertThrows(ObjectNotFoundException.class, () -> cart.addProduct(productId, quantity));
  }

  @Test
  public void addProductWithNegativeNumber() {
    long productId = 1;
    int quantity = -1;
    assertThrows(NegativeNumberException.class, () -> cart.addProduct(productId, quantity));
  }
}
