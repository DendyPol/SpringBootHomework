package spring_lesson_one.com.example.demo.service.conrainers;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {
  public static final String IMAGE_VERSION = "postgres:latest";
  public static final String DATABASE_NAME = "test";
  public static PostgreSQLContainer container;

  public PostgresTestContainer() {
    super(IMAGE_VERSION);
  }

  public static PostgreSQLContainer getInstance() {
    if (container == null) {
      container = new PostgresTestContainer().withDatabaseName(DATABASE_NAME);
    }
    return container;
  }

  public void start() {
    super.start();
    System.setProperty("DB_URL", container.getJdbcUrl());
    System.setProperty("DB_USERNAME", container.getUsername());
    System.setProperty("DB_PASSWORD", container.getPassword());
  }

  public void stop() {
  }
}
