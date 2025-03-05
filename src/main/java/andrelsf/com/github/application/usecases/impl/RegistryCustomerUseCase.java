package andrelsf.com.github.application.usecases.impl;

import static andrelsf.com.github.application.utils.Mapper.requestToDomain;

import andrelsf.com.github.application.usecases.RegistryCustomer;
import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.infra.controllers.http.requests.PostCustomerRequest;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegistryCustomerUseCase implements RegistryCustomer {

  @Inject
  CustomerRepository customerRepository;

  @Override
  public String execute(PostCustomerRequest postCustomerRequest) {
    final CustomerDomain customerDomain = requestToDomain(postCustomerRequest);
    customerRepository.save(customerDomain);
    return customerDomain.getId();
  }
}
