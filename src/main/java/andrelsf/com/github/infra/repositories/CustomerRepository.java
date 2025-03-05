package andrelsf.com.github.infra.repositories;

import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.queries.QueryParams;
import java.util.Set;

public interface CustomerRepository {
  Set<CustomerDomain> getAll(QueryParams queryParams);
  void save(CustomerDomain customer);
  CustomerDomain findById(CustomUUID customerId);
  void update(CustomUUID customerId, CustomerDomain customer);

}
