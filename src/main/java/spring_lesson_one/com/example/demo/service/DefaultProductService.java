package spring_lesson_one.com.example.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring_lesson_one.com.example.demo.jpa.ProductRepository;
import spring_lesson_one.com.example.demo.dto.ProductCreateDTO;
import spring_lesson_one.com.example.demo.dto.ProductDTO;
import spring_lesson_one.com.example.demo.dto.ProductUpdateDTO;
import spring_lesson_one.com.example.demo.exception.ObjectNotFoundException;
import spring_lesson_one.com.example.demo.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {
  private final ProductRepository productDAO;

  public List<ProductDTO> findAll() {
    return productDAO.findAll().stream()
      .map(ProductMapper::mapToProductDTO)
      .collect(Collectors.toList());
  }

  public ProductDTO findById(long id) {
    var product = productDAO.findById(id)
      .orElseThrow(() -> new ObjectNotFoundException(String.format("Product c ID %d не найден", id)));
    return ProductMapper.mapToProductDTO(product);
  }

  public ProductDTO create(ProductCreateDTO productCreateDTO) {
    var product = ProductMapper.mapToProduct(productCreateDTO);
    var savedProduct = productDAO.save(product);
    return ProductMapper.mapToProductDTO(savedProduct);
  }

  public void delete(long id) {
    productDAO.findById(id).orElseThrow(() -> new ObjectNotFoundException(String.format("Product c ID %d не найден", id)));
    productDAO.deleteById(id);
  }

  public ProductDTO update(Long id, ProductUpdateDTO productUpdateDTO) {
    var product = productDAO.findById(id)
      .orElseThrow(() -> new ObjectNotFoundException(String.format("Product c ID %d не найден", id)));
    product.setName(productUpdateDTO.getName());
    product.setPrice(productUpdateDTO.getPrice());
    var savedProduct = productDAO.save(product);
    return ProductMapper.mapToProductDTO(savedProduct);
  }
}
