package andrelsf.com.github.domain.vo;

public class Address {

  public final String value;


  public Address(String value) {
    if (value.isBlank()) {
      throw new IllegalArgumentException("Invalid address");
    }
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
