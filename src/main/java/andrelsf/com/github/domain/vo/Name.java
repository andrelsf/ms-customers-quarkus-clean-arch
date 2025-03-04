package andrelsf.com.github.domain.vo;

public class Name {

  private final String value;

  public Name(String value) {
    if (value.isBlank() || !value.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+(?: [A-Za-zÀ-ÖØ-öø-ÿ]+)+$")) {
      throw new IllegalArgumentException("Invalid name. ".concat(value));
    }
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
