package spring_lesson_one.com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_lesson_one.com.example.demo.jpa.Product;
import spring_lesson_one.com.example.demo.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  @PostMapping("/create")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    productService.create(product);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public ResponseEntity<Optional<List<Product>>> findAll() {
    final Optional<List<Product>> productList = Optional.ofNullable(productService.findAll());
    return productList.map(product -> product.isEmpty()
        ? new ResponseEntity<>(productList, HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(productList, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findById(@PathVariable(name = "id") long id) {
    final Optional<Product> product = Optional.ofNullable(productService.findById(id));
    return product.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable(name = "id") long id) {
    productService.delete(id);
  }
}
