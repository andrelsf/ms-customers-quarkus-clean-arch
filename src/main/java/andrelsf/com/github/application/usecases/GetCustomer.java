package andrelsf.com.github.application.usecases;

import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;

public interface GetCustomer {

  CustomerResponse execute(final CustomUUID customerId);

}
