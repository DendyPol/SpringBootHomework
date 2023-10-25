package spring_lesson_one.com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalHandlerException {
  @ExceptionHandler({ObjectNotFoundException.class, NegativeNumberException.class})
  public void notFoundException(Exception e) {
    log.warn(e.getMessage());
  }
}
