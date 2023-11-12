package spring_lesson_one.com.example.demo.exception;

import java.util.NoSuchElementException;

public class ObjectNotFoundException extends NoSuchElementException {
  public ObjectNotFoundException(String str) {
    super(str);
  }
}
