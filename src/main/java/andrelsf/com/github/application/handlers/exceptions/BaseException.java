package andrelsf.com.github.application.handlers.exceptions;

public class BaseException extends RuntimeException {

  private final String message;

  public BaseException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
