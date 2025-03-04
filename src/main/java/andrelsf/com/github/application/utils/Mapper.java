package andrelsf.com.github.application.utils;

import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import andrelsf.com.github.infra.controllers.http.responses.IdentificationResponse;

public interface Mapper {

  static CustomerResponse customerEntityToResponse(final CustomerDomain customerDomain) {
    return new CustomerResponse(
      customerDomain.getId(),
        customerDomain.getName(),
        customerDomain.getEmail(),
        customerDomain.getCellPhone(),
        customerDomain.getDateOfBirth().toString(),
        new IdentificationResponse(
            customerDomain.getIdentificationNumber().getType(),
            customerDomain.getIdentificationNumber().number())
    );
  }

}
