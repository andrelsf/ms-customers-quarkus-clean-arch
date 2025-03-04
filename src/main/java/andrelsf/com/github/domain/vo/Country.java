package andrelsf.com.github.domain.vo;

public class Country {

  private final String value;

  public Country(String country) {
    if (country.isBlank()) {
      throw new IllegalArgumentException("Invalid country");
    }
    this.value = country;
  }

  public String getValue() {
    return this.value;
  }
}
