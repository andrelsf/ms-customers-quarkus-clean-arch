package andrelsf.com.github.infra.repositories.impl;

import static andrelsf.com.github.application.utils.Mapper.domainToModel;

import andrelsf.com.github.application.utils.Mapper;
import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.queries.QueryParams;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import andrelsf.com.github.infra.repositories.models.CustomerModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerRepositoryImpl implements CustomerRepository, PanacheRepository<CustomerModel> {

  @Override
  public Set<CustomerDomain> getAll(QueryParams params) {
    return this.findAll()
        .page(params.page(), params.size())
        .stream()
        .map(Mapper::modelToDomain)
        .collect(Collectors.toSet());
  }

  @Override
  @Transactional
  public void save(CustomerDomain customer) {
    final CustomerModel customerModel = domainToModel(customer);
    this.persistAndFlush(customerModel);
  }

  @Override
  public CustomerDomain findById(CustomUUID customerId) {
    return this.find("customerId", customerId.getValue())
        .singleResultOptional()
        .map(Mapper::modelToDomain)
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Customer not found by Id. ".concat(customerId.getValue())));
  }
}
