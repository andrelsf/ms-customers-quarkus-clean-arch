package andrelsf.com.github.domain.vo;

public class ZipCode {

  private final String value;

  public ZipCode(String zipCode) {
    if (zipCode.isBlank()) {
      throw new IllegalArgumentException("Invalid zipCode");
    }
    this.value = zipCode;
  }

  public String getValue() {
    return this.value;
  }
}
