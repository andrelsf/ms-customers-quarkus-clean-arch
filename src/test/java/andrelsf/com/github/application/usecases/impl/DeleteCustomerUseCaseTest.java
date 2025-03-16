package andrelsf.com.github.application.usecases.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteCustomerUseCaseTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private DeleteCustomerUseCase deleteCustomerUseCase;

  @Test
  void test_should_delete_customer_by_customerId() {
    final CustomUUID customerId = CustomUUID.generate();
    doNothing()
        .when(customerRepository).delete(customerId);
    assertDoesNotThrow(() -> deleteCustomerUseCase.execute(customerId));
    verify(customerRepository, times(1))
        .delete(customerId);
  }

}