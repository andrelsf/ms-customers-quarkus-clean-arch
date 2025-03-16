package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CityTest {

  @Test
  void test_CityValid() {
    final City city = new City("Brasilia");
    assertThat(city)
        .isNotNull()
        .isInstanceOf(City.class);
    assertThat(city.getValue())
        .isNotBlank();
  }

  @Test
  void test_CityInvalid() {
    assertThatThrownBy(() -> new City(""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid city");
  }
}