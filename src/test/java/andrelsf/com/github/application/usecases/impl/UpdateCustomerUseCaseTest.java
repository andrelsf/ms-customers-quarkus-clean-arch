package andrelsf.com.github.application.usecases.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import andrelsf.com.github.domain.vo.CustomUUID;
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
class UpdateCustomerUseCaseTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private UpdateCustomerUseCase updateCustomerUseCase;

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
  void test_should_update_customer_by_id() {
    final String customerIdAsString = CustomUUID.generate().getValue();
    final CustomUUID customerId = new CustomUUID(customerIdAsString);
    assertDoesNotThrow(() ->
        updateCustomerUseCase.execute(customerId, joseNomeFacil));
  }
}