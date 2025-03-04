package andrelsf.com.github.domain.vo;

public class Email {

  private final String value;

  public Email(String value) {
    if (value.isBlank() || !value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
      throw new IllegalArgumentException("Invalid EMAIL. ".concat(value));
    }
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
