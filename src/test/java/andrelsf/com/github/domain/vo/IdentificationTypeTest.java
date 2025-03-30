package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class IdentificationTypeTest {

  @Test
  void test_identificationType_cpf_valid() {
    final String identificationTypeCPFExpected = "CPF";
    final IdentificationType identificationType = assertDoesNotThrow(() ->
        IdentificationType.getTypeBy(identificationTypeCPFExpected));
    assertThat(identificationType)
        .isNotNull()
        .isInstanceOf(IdentificationType.class);
    assertThat(identificationType.name())
        .isNotBlank()
        .isEqualTo(identificationTypeCPFExpected);
  }

  @Test
  void test_identificationType_invalid() {
    final String identificationTypeUnknown = "unknown";
    assertThatThrownBy(() -> IdentificationType.getTypeBy(identificationTypeUnknown))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid Identification type.");
  }
}