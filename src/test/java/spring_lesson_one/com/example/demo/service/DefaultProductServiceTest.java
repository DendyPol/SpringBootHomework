package spring_lesson_one.com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring_lesson_one.com.example.demo.DemoApplication;
import spring_lesson_one.com.example.demo.dto.ProductCreateDTO;
import spring_lesson_one.com.example.demo.dto.ProductDTO;
import spring_lesson_one.com.example.demo.dto.ProductUpdateDTO;
import spring_lesson_one.com.example.demo.exception.ObjectNotFoundException;
import spring_lesson_one.com.example.demo.service.config.ContainerEnvironment;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DefaultProductServiceTest extends ContainerEnvironment {
  @Autowired
  ProductService productService;

  @Test
  void createProduct() {
    var productCreateDTO = productService.create(ProductCreateDTO.builder()
      .name("Product")
      .price(BigDecimal.valueOf(10.0))
      .build());
    var expectedProduct = ProductDTO.builder()
      .id(productCreateDTO.getId())
      .name(productCreateDTO.getName())
      .price(productCreateDTO.getPrice())
      .build();
    assertEquals(productCreateDTO, expectedProduct);
  }

  @Test
  void updateProduct_ThrowsObjectNotFoundException() {
    var productCreateDTO = productService.create(ProductCreateDTO.builder()
      .name("Product")
      .price(BigDecimal.valueOf(22.0))
      .build());
    var productUpdateDTO = ProductUpdateDTO.builder()
      .id(2L)
      .name("Updated Product")
      .price(BigDecimal.valueOf(22.2))
      .build();
    var updatedProduct = productService.update(productCreateDTO.getId(), productUpdateDTO);
    var expectedProduct = ProductDTO.builder()
      .id(updatedProduct.getId())
      .name(updatedProduct.getName())
      .price(updatedProduct.getPrice())
      .build();
    var fakeId = 999L;
    assertEquals(updatedProduct.getId(), expectedProduct.getId());
    assertEquals(updatedProduct.getName(), expectedProduct.getName());
    assertEquals(updatedProduct.getPrice(), expectedProduct.getPrice());
    assertThrows(ObjectNotFoundException.class, () -> productService.update(fakeId, productUpdateDTO));
  }

  @Test
  void findAllProducts() {
    var productsCreate = List.of
      (productService.create(ProductCreateDTO.builder()
          .name("Product")
          .price(BigDecimal.valueOf(22.2))
          .build()),
        productService.create(ProductCreateDTO.builder()
          .name("Product 2")
          .price(BigDecimal.valueOf(10.2))
          .build()));
    assertEquals(productsCreate, productService.findAll());
  }

  @Test
  void deleteProduct() {
    var productCreate = productService.create(ProductCreateDTO.builder()
      .name("Product")
      .price(BigDecimal.valueOf(22.2))
      .build());
    productService.delete(productCreate.getId());
    assertThrows(ObjectNotFoundException.class, () -> productService.findById(productCreate.getId()));
  }

  @Test
  void findByIdProduct() {
    var productCreate = productService.create(ProductCreateDTO.builder()
      .name("Product")
      .price(BigDecimal.valueOf(22.2))
      .build());
    assertEquals(productCreate, productService.findById(productCreate.getId()));
  }
}
