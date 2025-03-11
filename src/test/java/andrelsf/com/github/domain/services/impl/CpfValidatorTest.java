package andrelsf.com.github.domain.services.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CpfValidatorTest {

  @Test
  void test_CpfValidator_valid() {
    final String cpf = "71510309080";

    assertDoesNotThrow(() ->
        new CpfValidator().validate(cpf));
  }

  @Test
  void test_CpfValidator_validFormatted() {
    final String cpf = "715.103.090-80";

    assertDoesNotThrow(() ->
        new CpfValidator().validate(cpf));
  }

  @Test
  void test_CpfValidator_invalidLength() {
    final String invalidCpf = "1112223334";
    assertThatThrownBy(() -> new CpfValidator().validate(invalidCpf))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid length for CPF=".concat(invalidCpf));
  }

  @Test
  void test_CpfValidator_invalid_allEqual() {
    final String invalidCpf = "11111111111";
    assertThatThrownBy(() -> new CpfValidator().validate(invalidCpf))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid CPF all numbers are the same. ".concat(invalidCpf));
  }

  @Test
  void test_CpfValidator_invalid_firstVerifierDigit() {
    final String invalidCpf = "71510309090";
    assertThatThrownBy(() -> new CpfValidator().validate(invalidCpf))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid first verifier digit.");
  }

  @Test
  void test_CpfValidator_invalid_secondVerifierDigit() {
    final String invalidCpf = "71510309081";
    assertThatThrownBy(() -> new CpfValidator().validate(invalidCpf))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid second verifier digit.");
  }
}