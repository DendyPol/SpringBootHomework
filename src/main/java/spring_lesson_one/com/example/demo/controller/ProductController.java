package spring_lesson_one.com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring_lesson_one.com.example.demo.dto.ProductCreateDTO;
import spring_lesson_one.com.example.demo.dto.ProductDTO;
import spring_lesson_one.com.example.demo.dto.ProductUpdateDTO;
import spring_lesson_one.com.example.demo.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  @PostMapping("create")
  public ProductDTO create(@RequestBody ProductCreateDTO productCreateDTO) {
    return productService.create(productCreateDTO);
  }

  @GetMapping("all")
  public List<ProductDTO> findAll() {
    return productService.findAll();
  }

  @GetMapping("{id}")
  public ProductDTO findById(@PathVariable("id") long id) {
    return productService.findById(id);
  }

  @DeleteMapping("{id}")
  public void deleteProduct(@PathVariable("id") long id) {
    productService.delete(id);
  }

  @PutMapping("{id}")
  public ProductDTO update(@PathVariable("id") Long id, @RequestBody ProductUpdateDTO productUpdateDTO) {
    return productService.update(id, productUpdateDTO);
  }
}
