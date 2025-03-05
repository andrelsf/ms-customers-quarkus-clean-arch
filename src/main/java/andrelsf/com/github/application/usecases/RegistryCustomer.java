package andrelsf.com.github.application.usecases;

import andrelsf.com.github.infra.controllers.http.requests.CustomerRequest;

public interface RegistryCustomer {

  String execute(final CustomerRequest customerRequest);
}
