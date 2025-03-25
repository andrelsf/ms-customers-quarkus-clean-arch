package andrelsf.com.github.application.usecases;

import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.requests.AddressRequest;

public interface RegistryAddress {

  CustomUUID execute(CustomUUID customerId, AddressRequest addressRequest);
}
