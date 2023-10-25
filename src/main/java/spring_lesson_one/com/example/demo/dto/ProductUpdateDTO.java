package spring_lesson_one.com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDTO extends ProductDTO {
  public long id;
  public String name;
  public BigDecimal price;
}
