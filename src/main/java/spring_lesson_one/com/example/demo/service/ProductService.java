package spring_lesson_one.com.example.demo.service;

import spring_lesson_one.com.example.demo.jpa.Product;

import java.util.List;

public interface ProductService {
  List<Product> findAll();

  Product findById(long id);

  Product create(Product product);

  void delete(long id);
}
