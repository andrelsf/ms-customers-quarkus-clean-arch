package andrelsf.com.github.application.usecases.impl;

import static andrelsf.com.github.application.utils.Mapper.domainToResponse;

import andrelsf.com.github.application.usecases.GetCustomer;
import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetCustomerUseCase implements GetCustomer {

  @Inject CustomerRepository customerRepository;

  @Override
  public CustomerResponse execute(CustomUUID customerId) {
    final CustomerDomain customerDomain = customerRepository.findById(customerId);
    return domainToResponse(customerDomain);
  }
}
