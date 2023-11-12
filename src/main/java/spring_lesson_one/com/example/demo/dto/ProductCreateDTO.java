package spring_lesson_one.com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ProductCreateDTO {
  public String name;
  public BigDecimal price;
}
