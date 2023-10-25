package spring_lesson_one.com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO extends ProductDTO {
  public String name;
  public BigDecimal price;
}
