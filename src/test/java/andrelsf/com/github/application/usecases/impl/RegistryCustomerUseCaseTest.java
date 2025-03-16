package andrelsf.com.github.application.usecases.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import andrelsf.com.github.domain.vo.IdentificationType;
import andrelsf.com.github.infra.controllers.http.requests.CustomerRequest;
import andrelsf.com.github.infra.controllers.http.requests.IdentificationRequest;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistryCustomerUseCaseTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private RegistryCustomerUseCase registryCustomerUseCase;

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
  void test_should_register_a_new_customer_and_return_customerId() {
    doNothing()
        .when(customerRepository).save(any());
    final String customerId = registryCustomerUseCase.execute(joseNomeFacil);
    assertThat(customerId)
        .isNotBlank()
        .hasSize(32)
        .isAlphanumeric();
    verify(customerRepository, times(1)).save(any());
  }
}