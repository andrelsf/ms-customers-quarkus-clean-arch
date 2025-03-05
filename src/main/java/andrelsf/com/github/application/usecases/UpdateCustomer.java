package andrelsf.com.github.application.usecases;

import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.requests.CustomerRequest;

public interface UpdateCustomer {

  void execute(final CustomUUID customerId, CustomerRequest customerRequest);

}
