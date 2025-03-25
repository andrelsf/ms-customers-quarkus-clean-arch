package andrelsf.com.github.infra.repositories.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import andrelsf.com.github.application.handlers.exceptions.CustomerException.DuplicateKeyException;
import andrelsf.com.github.domain.entities.AddressDomain;
import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.domain.vo.AddressType;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.domain.vo.IdentificationType;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AddressRepositoryImplTest {

  @Inject
  CustomerRepositoryImpl customerRepository;
  @Inject
  AddressRepositoryImpl addressRepository;

  private CustomerDomain johnDoe;
  private CustomerDomain janeDoe;

  @BeforeEach
  void setUp() {
    johnDoe = new CustomerDomain(
        CustomUUID.generate().getValue(),
        "John Doe",
        "john.doe.test@test.com",
        "+5562922334477",
        LocalDate.of(1990, 1, 1),
        "115.939.200-59",
        IdentificationType.CPF.name(),
        Boolean.TRUE);
  }

  @Test
  @Order(1)
  void test_save_address_valid_and_invalid() {
    assertDoesNotThrow(() -> customerRepository.save(johnDoe));

    final AddressDomain addressDomain = buildAddressDomain();
    final CustomUUID addressId = assertDoesNotThrow(() ->
        addressRepository.save(addressDomain));
    assertThat(addressId)
        .isNotNull()
        .isInstanceOf(CustomUUID.class);
    assertThat(addressId.getValue())
        .isNotBlank()
        .hasSize(32);

    assertThatThrownBy(() ->
        addressRepository.save(addressDomain))
        .isInstanceOf(DuplicateKeyException.class)
        .hasMessage(
            "Address type already exists. ".concat(addressDomain.getType().name()));
  }

  @Test
  @Order(2)
  void test_getAll() {
    final CustomUUID customerId = new CustomUUID(johnDoe.getId());
    final Set<AddressDomain> addresses = addressRepository.getAll(customerId);
    assertThat(addresses)
        .isNotNull()
        .isInstanceOf(Set.class);
  }

  private AddressDomain buildAddressDomain() {
    return new AddressDomain(
        CustomUUID.generate().getValue(),
        johnDoe.getId(),
        AddressType.RESIDENTIAL.name(),
        "Address test",
        "CityTest",
        "StateTest",
        "TestCountry",
        "00123321");
  }
}