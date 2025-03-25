package andrelsf.com.github.application.usecases.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import andrelsf.com.github.application.usecases.GetCustomer;
import andrelsf.com.github.domain.entities.AddressDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.domain.vo.IdentificationType;
import andrelsf.com.github.infra.controllers.http.requests.AddressRequest;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import andrelsf.com.github.infra.controllers.http.responses.IdentificationResponse;
import andrelsf.com.github.infra.repositories.AddressRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistryAddressUseCaseTest {

  @Mock
  private GetCustomer getCustomer;
  @Mock
  private AddressRepository addressRepository;

  @InjectMocks
  private RegistryAddressUseCase registryAddressUseCase;

  @Test
  void test_success() {
    final CustomUUID customerId = CustomUUID.generate();
    final CustomUUID addressIdExpected = CustomUUID.generate();
    final AddressRequest addressRequest = buildAddressRequest();
    final CustomerResponse customerResponse = buildCustomerResponse(customerId);
    when(getCustomer.execute(customerId))
        .thenReturn(customerResponse);
    when(addressRepository.save(any(AddressDomain.class)))
        .thenReturn(addressIdExpected);

    final CustomUUID addressId = registryAddressUseCase.execute(customerId, addressRequest);

    assertThat(addressId)
        .isNotNull()
        .isInstanceOf(CustomUUID.class);
    assertThat(addressId.getValue())
        .isNotBlank()
        .isEqualTo(addressIdExpected.getValue());
  }

  private AddressRequest buildAddressRequest() {
    return new AddressRequest(
        "RESIDENTIAL",
        "Street test",
        "Test",
        "TS",
        "Brazil",
        "79045300");
  }

  private CustomerResponse buildCustomerResponse(final CustomUUID customerId) {
    return new CustomerResponse(customerId.getValue(),
        "Jose Nome Facil",
        "jose.facil@test.com",
        "+5562911223344",
        LocalDate.of(1990, 1, 1).toString(),
        new IdentificationResponse(IdentificationType.CPF.name(), "490.494.270-14"),
        Boolean.TRUE);
  }
}