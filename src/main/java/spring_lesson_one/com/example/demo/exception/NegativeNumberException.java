package spring_lesson_one.com.example.demo.exception;

public class NegativeNumberException extends IllegalArgumentException {
  public NegativeNumberException(String str) {
    super(str);
  }
}
