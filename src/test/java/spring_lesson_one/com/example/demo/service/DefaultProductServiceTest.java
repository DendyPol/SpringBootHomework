package spring_lesson_one.com.example.demo.service;

import org.assertj.core.api.WithAssertions;
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

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DefaultProductServiceTest extends ContainerEnvironment implements WithAssertions {
  @Autowired
  ProductService productService;

  @Test
  void createProduct() {
    var productCreateDTO = productService.create(ProductCreateDTO.builder()
      .name("Product")
      .price(BigDecimal.valueOf(10.0))
      .build());
    var expectedProduct = ProductDTO.builder()
      .id(productCreateDTO.id)
      .name(productCreateDTO.name)
      .price(productCreateDTO.price)
      .build();
    assertEquals(productCreateDTO, expectedProduct);
    assertEquals(productCreateDTO.hashCode(), expectedProduct.hashCode());
  }

  @Test
  void updateProduct() {
    var productCreateDTO = productService.create(ProductCreateDTO.builder()
      .name("Product")
      .price(BigDecimal.valueOf(22.0))
      .build());
    var productUpdateDTO = ProductUpdateDTO.builder()
      .id(2L)
      .name("Updated Product")
      .price(BigDecimal.valueOf(22.2))
      .build();
    var updatedProduct = productService.update(productCreateDTO.id, productUpdateDTO);
    var expectedProduct = ProductDTO.builder()
      .id(updatedProduct.id)
      .name(updatedProduct.name)
      .price(updatedProduct.price)
      .build();
    assertAll(
      () -> assertEquals(updatedProduct, expectedProduct),
      () -> assertEquals(updatedProduct.hashCode(), expectedProduct.hashCode())
    );
  }

  @Test
  void updateProduct_nonexistent_throwsObjectNotFoundException() {
    var productUpdateDTO = ProductUpdateDTO.builder()
      .id(989898L)
      .name("Product")
      .price(BigDecimal.valueOf(22.2))
      .build();
    assertThrows(ObjectNotFoundException.class, () -> productService.update(productUpdateDTO.id, productUpdateDTO));
  }

  @Test
  void findAllProducts() {
    var productCreateOne = productService.create(ProductCreateDTO.builder()
      .name("Product")
      .price(BigDecimal.valueOf(22.2))
      .build());
    var productCreateTwo = productService.create(ProductCreateDTO.builder()
      .name("Product 2")
      .price(BigDecimal.valueOf(10.2))
      .build());
    var productsExpected = List.of(productCreateOne, productCreateTwo);
    assertThat(productsExpected).containsExactlyInAnyOrder(productCreateOne, productCreateTwo);
  }

  @Test
  void deleteProduct() {
    var productCreate = productService.create(ProductCreateDTO.builder()
      .name("Product")
      .price(BigDecimal.valueOf(22.2))
      .build());
    productService.delete(productCreate.id);
    assertThrows(ObjectNotFoundException.class, () -> productService.findById(productCreate.id));
  }

  @Test
  void deleteProduct_nonexistent_throwsObjectNotFoundException() {
    assertThrows(ObjectNotFoundException.class, () -> productService.findById(989898L));
  }

  @Test
  void findByIdProduct() {
    var productCreate = productService.create(ProductCreateDTO.builder()
      .name("Product")
      .price(BigDecimal.valueOf(22.2))
      .build());
    assertEquals(0, productCreate.price.compareTo(productService.findById(productCreate.id).price));
  }

  @Test
  void findByIdProduct_nonexistent_throwsObjectNotFoundException() {
    assertThrows(ObjectNotFoundException.class, () -> productService.findById(989898L));
  }
}
