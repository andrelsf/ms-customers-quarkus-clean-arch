package andrelsf.com.github.domain.services.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class CnpjValidatorTest {

  @Test
  void test_Cnpj_unformatted_valid() {
    final String cnpjValid = "12ABC34501DE35";
    assertDoesNotThrow(() -> new CnpjValidator().validate(cnpjValid));
  }

  @Test
  void test_Cnpj_formatted_valid() {
    final String cnpjValid = "12.ABC.345/01DE-35";
    assertDoesNotThrow(() -> new CnpjValidator().validate(cnpjValid));
  }

  @Test
  void test_Cnpj_old_valid() {
    final String cnpjValid = "03170664000197";
    assertDoesNotThrow(() -> new CnpjValidator().validate(cnpjValid));
  }

  @Test
  void test_Cnpj_old_valid_withFormatted() {
    final String cnpjValid = "03.170.664/0001-97";
    assertDoesNotThrow(() -> new CnpjValidator().validate(cnpjValid));
  }

  @Test
  void test_Cnpj_firstDV_invalid() {
    final String cnpjInvalid = "12.ABC.345/01DE-95";

    assertThatThrownBy(() -> new CnpjValidator().validate(cnpjInvalid))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid first verifier digit. dv=9");
  }

  @Test
  void test_Cnpj_secondDV_invalid() {
    final String cnpjInvalid = "12.ABC.345/01DE-39";

    assertThatThrownBy(() -> new CnpjValidator().validate(cnpjInvalid))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid second verifier digit. dv=9");
  }

  @Test
  void test_Cnpj_lengthInvalid() {
    final String cnpjInvalidLength = "12ABC34501DE3";
    assertThatThrownBy(() -> new CnpjValidator().validate(cnpjInvalidLength))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid length for CNPJ.");
  }
}