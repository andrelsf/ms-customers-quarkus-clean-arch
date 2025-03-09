package andrelsf.com.github.domain.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.domain.vo.Identification;
import andrelsf.com.github.domain.vo.IdentificationType;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class CustomerDomainTest {

  @Test
  void test_Customer_CPF_valid() {
    final String id = CustomUUID.generate().getValue();
    final String name = "Jose Nome Facil";
    final String email = "jose.facil@test.com";
    final String cellPhone = "+5562911223344";
    final LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
    final String identificationNumber = "493.107.281-08";
    final String identificationNumberType = IdentificationType.CPF.name();

    final CustomerDomain customerDomain = assertDoesNotThrow(() ->
        new CustomerDomain(
            id, name, email, cellPhone, dateOfBirth, identificationNumber, identificationNumberType, Boolean.TRUE));
    assertThat(customerDomain)
        .isNotNull()
        .isInstanceOf(CustomerDomain.class);
    assertThat(customerDomain.getId())
        .isNotBlank()
        .isEqualTo(id);
    assertThat(customerDomain.getName())
        .isNotBlank()
        .isEqualTo(name);
    assertThat(customerDomain.getEmail())
        .isNotBlank()
        .isEqualTo(email);
    assertThat(customerDomain.getCellPhone())
        .isNotBlank()
        .isEqualTo(cellPhone);
    assertThat(customerDomain.getDateOfBirth())
        .isNotNull()
        .isInstanceOf(LocalDate.class)
        .isEqualTo(dateOfBirth);
    assertThat(customerDomain.getIdentification())
        .isNotNull()
        .isInstanceOf(Identification.class);
    assertThat(customerDomain.getIdentification().getType())
        .isNotBlank()
        .isEqualTo(identificationNumberType);
    assertThat(customerDomain.getIdentification().getNumber())
        .isNotBlank()
        .isEqualTo(identificationNumber);
  }

}