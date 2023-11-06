package spring_lesson_one.com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateDTO {
  public long id;
  public String name;
  public BigDecimal price;
}
