package andrelsf.com.github.application.usecases.impl;

import andrelsf.com.github.application.usecases.DeleteCustomer;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteCustomerUseCase implements DeleteCustomer {

  @Inject CustomerRepository customerRepository;

  @Override
  public void execute(CustomUUID customerId) {
    customerRepository.delete(customerId);
  }
}
