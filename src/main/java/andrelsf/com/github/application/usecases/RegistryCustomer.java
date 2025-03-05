package andrelsf.com.github.application.usecases;

import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.requests.PostCustomerRequest;

public interface RegistryCustomer {

  String execute(final PostCustomerRequest postCustomerRequest);
}
