package andrelsf.com.github.application.usecases.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import andrelsf.com.github.domain.entities.AddressDomain;
import andrelsf.com.github.domain.vo.AddressType;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.responses.AddressResponse;
import andrelsf.com.github.infra.repositories.AddressRepository;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAddressesUseCaseTest {

  @Mock
  private AddressRepository addressRepository;

  @InjectMocks
  private GetAddressesUseCase getAddressesUseCase;

  @Test
  void test_getAllAddress_by_id() {
    final CustomUUID customerId = CustomUUID.generate();
    final AddressDomain addressDomain = buildAddressDomain(customerId);
    when(addressRepository.getAll(customerId))
        .thenReturn(Set.of(addressDomain));

    final Set<AddressResponse> addresses = getAddressesUseCase.execute(customerId);

    assertThat(addresses)
        .isNotNull()
        .isInstanceOf(Set.class)
        .hasSize(1);
  }

  private AddressDomain buildAddressDomain(final CustomUUID customerId) {
    return new AddressDomain(
        CustomUUID.generate().getValue(),
        customerId.getValue(),
        AddressType.RESIDENTIAL.name(),
        "Rua Atibaia Jardim Noroeste",
        "Campo Grande",
        "MS",
        "Brasil",
        "79045300");
  }
}