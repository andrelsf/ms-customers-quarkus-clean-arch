package andrelsf.com.github.application.usecases.impl;

import andrelsf.com.github.application.usecases.GetAllCustomers;
import andrelsf.com.github.application.utils.Mapper;
import andrelsf.com.github.infra.controllers.http.queries.QueryParams;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class GetAllCustomersUseCase implements GetAllCustomers {


  @Inject CustomerRepository customerRepository;

  @Override
  public Set<CustomerResponse> execute(final QueryParams queryParams) {
    return customerRepository.getAll(queryParams)
        .stream()
        .map(Mapper::domainToResponse)
        .collect(Collectors.toSet());
  }
}
