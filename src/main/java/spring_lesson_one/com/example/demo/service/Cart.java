package spring_lesson_one.com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import spring_lesson_one.com.example.demo.exception.NegativeNumberException;
import spring_lesson_one.com.example.demo.jpa.Product;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@AllArgsConstructor
public class Cart {
  private final Map<Product, Integer> basket = new HashMap<>();
  private final ProductService productService;

  public Product addProduct(long id, int quantity) {
    if (quantity < 0) {
      throw new NegativeNumberException("Количество товара не может быть отрицательным");
    }
    var product = productService.findById(id);
    basket.put(product, quantity);
    return product;
  }

  public void deleteProduct(long id, int quantity) {
    var product = productService.findById(id);
    if (product != null) {
      basket.compute(product, (key, value) -> value != null ? value - quantity : null);
    }
  }
}
