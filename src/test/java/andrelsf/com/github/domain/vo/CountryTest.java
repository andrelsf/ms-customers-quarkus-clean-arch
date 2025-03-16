package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CountryTest {

  @Test
  void test_CountryValid() {
    final Country country = new Country("Brazil");
    assertThat(country)
        .isNotNull()
        .isInstanceOf(Country.class);
    assertThat(country.getValue())
        .isNotBlank();
  }

  @Test
  void test_CountryInvalid() {
    assertThatThrownBy(() -> new Country(""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid country");
  }
}