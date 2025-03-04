package andrelsf.com.github.domain.vo;

public class AddressType {

  public final String value;

  public AddressType(String value) {
    if (value.isBlank()) {
      throw new IllegalArgumentException("Invalid address type. ".concat(value));
    }
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
