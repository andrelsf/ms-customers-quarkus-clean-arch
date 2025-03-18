package andrelsf.com.github.infra.repositories.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import andrelsf.com.github.application.handlers.exceptions.CustomerException.DuplicateKeyException;
import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.domain.vo.IdentificationType;
import andrelsf.com.github.infra.controllers.http.queries.QueryParams;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


@QuarkusTest
class CustomerRepositoryImplTest {

  @Inject
  CustomerRepositoryImpl customerRepository;

  private QueryParams queryParams;
  private CustomerDomain johnDoe;
  private CustomerDomain janeDoe;
  private CustomerDomain joseNomeFacil;

  @BeforeEach
  void setUp() {
    queryParams = new QueryParams(0, 10);
    johnDoe = new CustomerDomain(
        CustomUUID.generate().getValue(),
        "John Doe",
        "john.doe@test.com",
        "+5562922334455",
        LocalDate.of(1990, 1, 1),
        "27995081030",
        IdentificationType.CPF.name(),
        Boolean.TRUE);
    janeDoe = new CustomerDomain(
        CustomUUID.generate().getValue(),
        "Jane Doe",
        "Jane.doe@test.com",
        "+5562944332211",
        LocalDate.of(1990, 1, 1),
        "10647269090",
        IdentificationType.CPF.name(),
        Boolean.TRUE);
    joseNomeFacil = new CustomerDomain(
        CustomUUID.generate().getValue(),
        "Jose Nome Facil",
        "jose.facil@test.com",
        "+5562911223344",
        LocalDate.of(1990, 1, 1),
        "490.494.270-14",
        IdentificationType.CPF.name(),
        Boolean.TRUE);
  }

  @Test
  @Order(1)
  void test_save_success() {
    assertDoesNotThrow(() ->
        customerRepository.save(johnDoe));
    assertDoesNotThrow(() ->
        customerRepository.save(janeDoe));
  }

  @Test
  @Order(2)
  void test_getAll() {
    final Set<CustomerDomain> customers = customerRepository.getAll(queryParams);
    assertThat(customers)
        .isNotEmpty()
        .hasSize(3);
  }

  @Test
  @Order(3)
  void test_save_fail() {
    assertThatThrownBy(() -> customerRepository.save(johnDoe))
        .isInstanceOf(DuplicateKeyException.class)
        .hasMessage("Duplicate key already exists identification number. ".concat(
            johnDoe.getIdentification().getNumber()));
  }

  @Test
  @Order(4)
  void test_findById_fail() {
    final CustomUUID customerId = new CustomUUID(joseNomeFacil.getId());
    assertThatThrownBy(() -> customerRepository.findById(customerId))
        .isInstanceOf(EntityNotFoundException.class)
        .hasMessage("Customer not found by Id. ".concat(joseNomeFacil.getId()));
  }

  @Test
  @Order(5)
  void test_findById_success() {
    assertDoesNotThrow(() ->
        customerRepository.save(joseNomeFacil));
    final CustomUUID customerId = new CustomUUID(joseNomeFacil.getId());
    final CustomerDomain customerDomain = customerRepository.findById(customerId);
    assertThat(customerDomain)
        .isNotNull()
        .isInstanceOf(CustomerDomain.class);
    assertThat(customerDomain.getId())
        .isNotBlank();
    assertThat(customerDomain.getName())
        .isNotBlank();
  }

  @Test
  @Order(6)
  void test_update_success() {
    final CustomerDomain bobDoe = new CustomerDomain(
        CustomUUID.generate().getValue(),
        "Bob Doe",
        "bob.doe@test.com",
        "+5562900112233",
        LocalDate.of(1990, 2, 15),
        "461.358.050-10",
        IdentificationType.CPF.name(),
        Boolean.TRUE);
    assertDoesNotThrow(() ->
        customerRepository.save(bobDoe));
    final CustomUUID customerId = new CustomUUID(bobDoe.getId());
    CustomerDomain joseNomeFacilUpdate = new CustomerDomain(
        bobDoe.getId(),
        "BOB DOE",
        "jose.facil@gmail.com",
        "+5562900112233",
        LocalDate.of(1990, 12, 1),
        "461.358.050-10",
        IdentificationType.CPF.name(),
        Boolean.FALSE);

    assertDoesNotThrow(() ->
        customerRepository.update(customerId, joseNomeFacilUpdate));
    final CustomerDomain customerDomainUpdated = customerRepository.findById(customerId);
    assertThat(customerDomainUpdated)
        .isNotNull()
        .isInstanceOf(CustomerDomain.class);
    assertThat(customerDomainUpdated.getId())
        .isNotBlank()
        .isEqualTo(customerId.getValue());
    assertThat(customerDomainUpdated.getEmail())
        .isNotBlank()
        .isNotEqualTo(bobDoe.getEmail());
    assertThat(customerDomainUpdated.getDateOfBirth())
        .isNotNull()
        .isNotEqualTo(bobDoe.getDateOfBirth());
    assertThat(customerDomainUpdated.isActive().getValue())
        .isEqualTo(Boolean.FALSE);
  }

  @Test
  @Order(8)
  void test_update_fail() {
    final String customerIdAsString = CustomUUID.generate().getValue();
    final CustomUUID customerId = new CustomUUID(customerIdAsString);
    assertThatThrownBy(() -> customerRepository.update(customerId, joseNomeFacil))
        .isInstanceOf(EntityNotFoundException.class)
        .hasMessage("Customer not found by Id. ".concat(customerIdAsString));
  }

  @Test
  @Order(9)
  void test_delete_success() {
    final CustomerDomain aliceDoe = new CustomerDomain(
        CustomUUID.generate().getValue(),
        "Alice Doe",
        "alice.doe@test.com",
        "+5562900112299",
        LocalDate.of(1995, 7, 1),
        "373.932.460-07",
        IdentificationType.CPF.name(),
        Boolean.TRUE);
    assertDoesNotThrow(() -> customerRepository.save(aliceDoe));
    final CustomUUID customerId = new CustomUUID(aliceDoe.getId());
    assertDoesNotThrow(() -> customerRepository.delete(customerId));
    final CustomerDomain customerDomainUpdated = customerRepository.findById(customerId);
    assertThat(customerDomainUpdated)
        .isNotNull()
        .isInstanceOf(CustomerDomain.class);
    assertThat(customerDomainUpdated.getId())
        .isNotBlank()
        .isEqualTo(customerId.getValue());
    assertThat(customerDomainUpdated.isActive().getValue())
        .isEqualTo(Boolean.FALSE);
  }

  @Test
  @Order(10)
  void test_delete_fail() {
    final String customerIdAsString = CustomUUID.generate().getValue();
    final CustomUUID customerId = new CustomUUID(customerIdAsString);
    assertThatThrownBy(() -> customerRepository.delete(customerId))
        .isInstanceOf(EntityNotFoundException.class)
        .hasMessage("Customer not found by Id. ".concat(customerIdAsString));
  }
}