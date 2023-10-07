package spring_lesson_one.com.example.demo.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Product {
  @Id
  private Long id;
  private String name;
  private BigDecimal price;
}
