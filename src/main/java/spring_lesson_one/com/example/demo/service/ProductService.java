package spring_lesson_one.com.example.demo.service;

import spring_lesson_one.com.example.demo.dto.ProductCreateDTO;
import spring_lesson_one.com.example.demo.dto.ProductDTO;
import spring_lesson_one.com.example.demo.dto.ProductUpdateDTO;

import java.util.List;

public interface ProductService {
  List<ProductDTO> findAll();

  ProductDTO findById(long id);

  ProductDTO create(ProductCreateDTO productCreateDTO);

  void delete(long id);

  ProductDTO update(Long id, ProductUpdateDTO productUpdateDTO);
}
