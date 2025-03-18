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
  private Set<CustomerDomain> allCustomers;

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
        .hasSize(2);
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
}