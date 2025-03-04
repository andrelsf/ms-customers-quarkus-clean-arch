package andrelsf.com.github.domain.vo;

public class CellPhone {

  private final String value;

  public CellPhone(String value) {
    if (value.isBlank() || !value.matches("^\\+(\\d{1,3})\\s?(\\d{2})\\s?9\\d{4}\\-?\\d{4}$")) {
      throw new IllegalArgumentException("Invalid cell phone. ".concat(value));
    }
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
