package spring_lesson_one.com.example.demo.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import spring_lesson_one.com.example.demo.exception.ObjectNotFoundException;

import java.util.List;

@AllArgsConstructor
@Component
public class ProductRepository {
  private final List<Product> products;

  public Product create(Product product) {
    products.add(product);
    return product;
  }

  public void delete(long id) {
    var product = findByIdThrows(id);
    products.remove(product);
  }

  public List<Product> findAll() {
    return products;
  }

  public Product findByIdThrows(long id) {
    return products.stream()
      .filter(product -> id == product.getId())
      .findFirst()
      .orElseThrow(() -> new ObjectNotFoundException(String.format("Product c ID %d не найден", id)));
  }
}
