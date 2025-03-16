package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ZipCodeTest {

  @Test
  void test_ZipCodeValid() {
    final ZipCode zipCode = new ZipCode("01000123");
    assertThat(zipCode)
        .isNotNull()
        .isInstanceOf(ZipCode.class);
    assertThat(zipCode.getValue())
        .isNotBlank();
  }

  @Test
  void test_ZipCodeInvalid() {
    assertThatThrownBy(() -> new ZipCode(""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid zipCode");
  }

}