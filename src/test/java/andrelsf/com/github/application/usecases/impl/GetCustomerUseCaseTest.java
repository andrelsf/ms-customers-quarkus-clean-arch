package andrelsf.com.github.application.usecases.impl;

import static andrelsf.com.github.application.utils.Mapper.requestToDomain;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.domain.vo.IdentificationType;
import andrelsf.com.github.infra.controllers.http.requests.CustomerRequest;
import andrelsf.com.github.infra.controllers.http.requests.IdentificationRequest;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetCustomerUseCaseTest {

  @Mock
  private CustomerRepository customerRepository;
  @InjectMocks
  private GetCustomerUseCase getCustomerUseCase;

  private CustomerRequest joseNomeFacil;

  @BeforeEach
  void setUp() {
    joseNomeFacil = new CustomerRequest(
        "Jose Nome Facil",
        "jose.facil@test.com",
        "+5562911223344",
        LocalDate.of(1990, 1, 1),
        new IdentificationRequest(IdentificationType.CPF.name(), "490.494.270-14"),
        Boolean.TRUE);
  }

  @Test
  void test_should_return_customer_by_query_customerId() {
    final CustomerDomain joseNomeFacilDomain = requestToDomain(joseNomeFacil);
    final CustomUUID customerId = new CustomUUID(joseNomeFacilDomain.getId());
    when(customerRepository.findById(customerId))
        .thenReturn(joseNomeFacilDomain);

    final CustomerResponse customerResponse = getCustomerUseCase.execute(customerId);

    assertThat(customerResponse)
        .isNotNull()
        .isInstanceOf(CustomerResponse.class);
    assertThat(customerResponse.customerId())
        .isNotBlank()
        .isEqualTo(customerId.getValue());
  }
}