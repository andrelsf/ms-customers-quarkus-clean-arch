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
  public Set<CustomerDomain> getAll(final QueryParams params) {
    return this.findAll()
        .page(params.page(), params.size())
        .stream()
        .map(Mapper::modelToDomain)
        .collect(Collectors.toSet());
  }

  @Override
  @Transactional
  public void save(final CustomerDomain customer) {
    final CustomerModel customerModel = domainToModel(customer);
    this.persistAndFlush(customerModel);
  }

  @Override
  public void delete(CustomUUID customerId) {
    this.find("customerId", customerId.getValue())
        .singleResultOptional()
        .ifPresentOrElse(customerModel -> {
          customerModel.inactivate();
          this.persistAndFlush(customerModel);
        }, () -> {
          throw new EntityNotFoundException("Customer not found by Id. ".concat(customerId.getValue()));
        });
  }

  @Override
  public CustomerDomain findById(final CustomUUID customerId) {
    return this.find("customerId", customerId.getValue())
        .singleResultOptional()
        .map(Mapper::modelToDomain)
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Customer not found by Id. ".concat(customerId.getValue())));
  }

  @Override
  @Transactional
  public void update(final CustomUUID customerId, final CustomerDomain customerDomain) {
    this.find("customerId", customerId.getValue())
        .singleResultOptional()
        .ifPresentOrElse(customerModel -> {
          customerModel.fillWith(customerDomain);
          this.persistAndFlush(customerModel);
        }, () -> {
          throw new EntityNotFoundException("Customer not found by Id. ".concat(customerId.getValue()));
        });
  }
}
