package andrelsf.com.github.application.usecases.impl;

import static andrelsf.com.github.application.utils.Mapper.addressRequestToDomain;

import andrelsf.com.github.application.usecases.GetCustomer;
import andrelsf.com.github.application.usecases.RegistryAddress;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.requests.AddressRequest;
import andrelsf.com.github.infra.repositories.AddressRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegistryAddressUseCase implements RegistryAddress {

  @Inject GetCustomer getCustomer;
  @Inject AddressRepository addressRepository;

  @Override
  public CustomUUID execute(CustomUUID customerId, AddressRequest addressRequest) {
    getCustomer.execute(customerId);
    return addressRepository.save(addressRequestToDomain(customerId, addressRequest));
  }
}
