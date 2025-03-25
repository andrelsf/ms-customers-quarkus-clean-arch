package andrelsf.com.github.application.usecases.impl;

import andrelsf.com.github.application.usecases.GetAddresses;
import andrelsf.com.github.application.utils.Mapper;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.responses.AddressResponse;
import andrelsf.com.github.infra.repositories.AddressRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class GetAddressesUseCase implements GetAddresses {

  @Inject AddressRepository addressRepository;

  @Override
  public Set<AddressResponse> execute(CustomUUID customerId) {
    return addressRepository.getAll(customerId)
        .stream()
        .map(Mapper::addressDomainToResponse)
        .collect(Collectors.toSet());
  }
}
