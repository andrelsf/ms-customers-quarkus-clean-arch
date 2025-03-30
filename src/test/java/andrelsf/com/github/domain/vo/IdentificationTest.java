package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class IdentificationTest {

  @Test
  void test_IdentificationNumber_CPFValid() {
    final String cpfValid = "71510309080";
    final Identification identification = assertDoesNotThrow(() ->
        new Identification(IdentificationType.CPF, cpfValid));
    assertThat(identification)
        .isNotNull()
        .isInstanceOf(Identification.class);
    assertThat(identification.getNumber())
        .isNotBlank()
        .isEqualTo(cpfValid);
    assertThat(identification.getType())
        .isNotBlank()
        .isEqualTo(IdentificationType.CPF.name());
  }

  @Test
  void test_IdentificationNumber_CNPJValid() {
    final String cnpjValid = "12.ABC.345/01DE-35";
    Identification identification = assertDoesNotThrow(
        () -> new Identification(IdentificationType.CNPJ, cnpjValid));
    assertThat(identification)
        .isNotNull()
        .isInstanceOf(Identification.class);
    assertThat(identification.getNumber())
        .isNotBlank()
        .isEqualTo(cnpjValid);
    assertThat(identification.getType())
        .isNotBlank()
        .isEqualTo(IdentificationType.CNPJ.name());
  }

  @Test
  void test_IdentificationNumber_CNPJIsEmpty() {
    assertThatThrownBy(() -> new Identification(IdentificationType.CNPJ, ""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid identification or number");
  }

  @Test
  void test_IdentificationNumber_CPFIsEmpty() {
    assertThatThrownBy(() -> new Identification(IdentificationType.CPF, ""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid identification or number");
  }

  @Test
  void test_identification_isEmpty() {
    assertThatThrownBy(() -> new Identification(null, ""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid identification or number");
  }
}