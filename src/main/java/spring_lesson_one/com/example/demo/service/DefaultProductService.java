package spring_lesson_one.com.example.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring_lesson_one.com.example.demo.jpa.Product;
import spring_lesson_one.com.example.demo.jpa.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {
  private final ProductRepository service;

  public List<Product> findAll() {
    return service.findAll();
  }

  public Product findById(long id) {
    return service.findByIdThrows(id);
  }

  public Product create(Product product) {
    return service.create(product);
  }

  public void delete(long id) {
    service.delete(id);
  }
}
