package spring_lesson_one.com.example.demo.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring_lesson_one.com.example.demo.dto.ProductCreateDTO;
import spring_lesson_one.com.example.demo.dto.ProductDTO;
import spring_lesson_one.com.example.demo.dto.ProductUpdateDTO;
import spring_lesson_one.com.example.demo.exception.ObjectNotFoundException;
import spring_lesson_one.com.example.demo.jpa.Product;
import spring_lesson_one.com.example.demo.jpa.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {
  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;

  public List<ProductDTO> findAll() {
    return productRepository.findAll().stream()
      .map(product -> modelMapper.map(product, ProductDTO.class))
      .collect(Collectors.toList());
  }

  public ProductDTO findById(long id) {
    var product = productRepository.findById(id)
      .orElseThrow(() -> new ObjectNotFoundException(String.format("Product c ID %d не найден", id)));
    return modelMapper.map(product, ProductDTO.class);
  }

  public ProductDTO create(ProductCreateDTO productCreateDTO) {
    var product = modelMapper.map(productCreateDTO, Product.class);
    return modelMapper.map(productRepository.save(product), ProductDTO.class);
  }

  public void delete(long id) {
    productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(String.format("Product c ID %d не найден", id)));
    productRepository.deleteById(id);
  }

  public ProductDTO update(Long id, ProductUpdateDTO productUpdateDTO) {
    var product = findById(id);
    modelMapper.map(productUpdateDTO, product);
    var updatedProduct = productRepository.save(modelMapper.map(product, Product.class));
    return modelMapper.map(updatedProduct, ProductDTO.class);
  }
}
