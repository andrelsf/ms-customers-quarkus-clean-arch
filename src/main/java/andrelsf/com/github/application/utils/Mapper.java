package andrelsf.com.github.application.utils;

import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.infra.controllers.http.requests.CustomerRequest;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import andrelsf.com.github.infra.controllers.http.responses.IdentificationResponse;
import andrelsf.com.github.infra.repositories.models.CustomerModel;

public interface Mapper {

  static CustomerResponse domainToResponse(final CustomerDomain customerDomain) {
    return new CustomerResponse(
        customerDomain.getId(),
        customerDomain.getName(),
        customerDomain.getEmail(),
        customerDomain.getCellPhone(),
        customerDomain.getDateOfBirth().toString(),
        new IdentificationResponse(
            customerDomain.getIdentification().getType(),
            customerDomain.getIdentification().getNumber()),
        customerDomain.isActive().getValue()
    );
  }

  static CustomerDomain modelToDomain(final CustomerModel customerModel) {
    return new CustomerDomain(
        customerModel.getCustomerId(),
        customerModel.getName(),
        customerModel.getEmail(),
        customerModel.getCellPhone(),
        customerModel.getDateOfBirth(),
        customerModel.getIdentificationNumber(),
        customerModel.getIdentificationType(),
        customerModel.isActive());
  }

  static CustomerModel domainToModel(final CustomerDomain customer) {
    return new CustomerModel(
        customer.getId(),
        customer.getName(),
        customer.getEmail(),
        customer.getCellPhone(),
        customer.getDateOfBirth(),
        customer.getIdentification().getType(),
        customer.getIdentification().getNumber(),
        customer.isActive().getValue());
  }

  static CustomerDomain requestToDomain(final CustomerRequest postRequest) {
    return CustomerDomain.create(
        postRequest.name(),
        postRequest.email(),
        postRequest.cellPhone(),
        postRequest.dateOfBirth(),
        postRequest.identification().number(),
        postRequest.identification().type(),
        postRequest.isActive());
  }
}
