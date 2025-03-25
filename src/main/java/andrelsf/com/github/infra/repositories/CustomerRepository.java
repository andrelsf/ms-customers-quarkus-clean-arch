package andrelsf.com.github.infra.repositories;

import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.queries.QueryParams;
import java.util.Set;

public interface CustomerRepository {
  void save(CustomerDomain customer);
  void delete(CustomUUID customerId);
  CustomerDomain findById(CustomUUID customerId);
  Set<CustomerDomain> getAll(QueryParams queryParams);
  void update(CustomUUID customerId, CustomerDomain customer);
}
