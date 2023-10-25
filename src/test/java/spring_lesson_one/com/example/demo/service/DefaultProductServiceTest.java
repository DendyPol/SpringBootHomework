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
  DefaultProductService defaultProductService;

  @Test
  void createProduct() {
    var productCreateDTO = new ProductCreateDTO();
    productCreateDTO.setName("Product");
    productCreateDTO.setPrice(BigDecimal.valueOf(10.0));
    ProductDTO createdProduct = defaultProductService.create(productCreateDTO);
    assertEquals("Product", createdProduct.getName());
    assertEquals(BigDecimal.valueOf(10.0), createdProduct.getPrice());
  }

  @Test
  void updateProduct() {
    var productCreateDTO = new ProductCreateDTO();
    productCreateDTO.setName("Product");
    productCreateDTO.setPrice(BigDecimal.valueOf(22.0));
    ProductDTO createdProduct = defaultProductService.create(productCreateDTO);
    var productUpdateDTO = new ProductUpdateDTO();
    productUpdateDTO.setName("Updated Product");
    productUpdateDTO.setPrice(BigDecimal.valueOf(22.2));
    ProductDTO productUpdate = defaultProductService.update(createdProduct.getId(), productUpdateDTO);
    assertEquals(createdProduct.getId(), productUpdate.getId());
    assertEquals("Updated Product", productUpdate.getName());
    assertEquals(BigDecimal.valueOf(22.2), productUpdate.getPrice());
  }

  @Test
  void findAllProducts() {
    var productsCreate = List.of
      (defaultProductService.create(new ProductCreateDTO("Product", BigDecimal.valueOf(22.2))),
        defaultProductService.create(new ProductCreateDTO("Product 2", BigDecimal.valueOf(10.2))));
    var findProduct = defaultProductService.findAll();
    assertEquals(productsCreate, findProduct);
  }

  @Test
  void deleteProduct() {
    var productCreate = defaultProductService.create(new ProductCreateDTO("Product", BigDecimal.valueOf(22.2)));
    defaultProductService.delete(productCreate.getId());
    assertThrows(ObjectNotFoundException.class, () -> defaultProductService.findById(productCreate.getId()));
  }

  @Test
  void findByIdProduct() {
    var productCreate = defaultProductService.create(new ProductCreateDTO("Product", BigDecimal.valueOf(22.2)));
    defaultProductService.findById(productCreate.getId());
    assertEquals(productCreate, defaultProductService.findById(productCreate.getId()));
  }
}
