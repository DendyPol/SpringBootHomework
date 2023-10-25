package spring_lesson_one.com.example.demo.mapper;

import spring_lesson_one.com.example.demo.dto.ProductDTO;
import spring_lesson_one.com.example.demo.jpa.Product;

public class ProductMapper {
  public static ProductDTO mapToProductDTO(Product product) {
    return new ProductDTO(
      product.getId(),
      product.getName(),
      product.getPrice()
    );
  }

  public static Product mapToProduct(ProductDTO productDTO) {
    return new Product(
      productDTO.getId(),
      productDTO.getName(),
      productDTO.getPrice()
    );
  }
}
