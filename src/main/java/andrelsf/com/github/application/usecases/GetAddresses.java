package andrelsf.com.github.application.usecases;

import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.responses.AddressResponse;
import java.util.Set;

public interface GetAddresses {

  Set<AddressResponse> execute(final CustomUUID customerId);
}
