package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CellPhoneTest {

  @Test
  void test_CellPhone_valid() {
    final CellPhone cellPhoneValid = new CellPhone("+5562982011122");
    assertThat(cellPhoneValid)
        .isNotNull()
        .isInstanceOf(CellPhone.class);
    assertThat(cellPhoneValid.getValue())
        .isNotBlank();
  }

  @Test
  void test_CellPhone_invalid_withoutInternationalCode() {
    final String cellPhoneInvalid = "5562999999999";
    assertThatThrownBy(() -> new CellPhone(cellPhoneInvalid))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid cell phone. ".concat(cellPhoneInvalid));
  }

  @Test
  void test_CellPhone_invalidNumber() {
    final String cellPhoneInvalid = "+556211223344";
    assertThatThrownBy(() -> new CellPhone(cellPhoneInvalid))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid cell phone. ".concat(cellPhoneInvalid));
  }

  @Test
  void test_CellPhone_invalidNumber_withInvalidCharacters() {
    final String cellPhoneInvalid = "+5562aabb334c";
    assertThatThrownBy(() -> new CellPhone(cellPhoneInvalid))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid cell phone. ".concat(cellPhoneInvalid));
  }
}