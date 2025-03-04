package andrelsf.com.github.domain.vo;

public class City {

  private final String value;

  public City(String city) {
    if (city.isBlank()) {
      throw new IllegalArgumentException("Invalid city");
    }
    this.value = city;
  }

  public String getValue() {
    return this.value;
  }
}
