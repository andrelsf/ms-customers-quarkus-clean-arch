package andrelsf.com.github.application.usecases.impl;

import static andrelsf.com.github.application.utils.Mapper.requestToDomain;

import andrelsf.com.github.application.usecases.UpdateCustomer;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.requests.CustomerRequest;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateCustomerUseCase implements UpdateCustomer {

  @Inject CustomerRepository customerRepository;

  @Override
  public void execute(final CustomUUID customerId, CustomerRequest customerRequest) {
    customerRepository.update(customerId, requestToDomain(customerRequest));
  }
}
