package spring_lesson_one.com.example.demo.service.config;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import spring_lesson_one.com.example.demo.service.conrainers.PostgresTestContainer;

@Testcontainers
public class ContainerEnvironment {
  @Container
  public static PostgreSQLContainer postgreSQLContainer = PostgresTestContainer.getInstance();
}
