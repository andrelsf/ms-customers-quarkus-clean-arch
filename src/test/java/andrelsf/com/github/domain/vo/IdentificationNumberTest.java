package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class IdentificationNumberTest {

  @Test
  void test_IdentificationNumber_CPFValid() {
    final String cpfValid = "71510309080";

    assertDoesNotThrow(() ->
        new IdentificationNumber(IdentificationNumberType.CPF, cpfValid));
  }

  @Test
  void test_IdentificationNumber_CNPJValid() {
    final String cnpjValid = "12.ABC.345/01DE-35";

    assertDoesNotThrow(() -> new IdentificationNumber(IdentificationNumberType.CNPJ, cnpjValid));
  }

  @Test
  void test_IdentificationNumber_CNPJIsEmpty() {
    assertThatThrownBy(() -> new IdentificationNumber(IdentificationNumberType.CNPJ, ""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid length for CNPJ");
  }

  @Test
  void test_IdentificationNumber_CPFIsEmpty() {
    assertThatThrownBy(() -> new IdentificationNumber(IdentificationNumberType.CPF, ""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid length for CPF");
  }
}