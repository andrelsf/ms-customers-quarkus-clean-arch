package andrelsf.com.github.infra.repositories.impl;

import static andrelsf.com.github.application.utils.Mapper.addressDomainToModel;

import andrelsf.com.github.application.handlers.exceptions.CustomerException.DuplicateKeyException;
import andrelsf.com.github.application.utils.Mapper;
import andrelsf.com.github.domain.entities.AddressDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.repositories.AddressRepository;
import andrelsf.com.github.infra.repositories.models.AddressModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class AddressRepositoryImpl implements AddressRepository, PanacheRepository<AddressModel> {

  @Override
  @Transactional
  public CustomUUID save(final AddressDomain addressDomain) {
    this.find("customerId = ?1 and type = ?2",
            addressDomain.getCustomerId(),
            addressDomain.getType())
        .singleResultOptional()
        .ifPresentOrElse(addressModel -> {
          throw new DuplicateKeyException(
              "Address type already exists. ".concat(addressModel.getType().name()));
        }, () ->
            this.persistAndFlush(addressDomainToModel(addressDomain)));
    return new CustomUUID(addressDomain.getId());
  }

  @Override
  public Set<AddressDomain> getAll(final CustomUUID customerId) {
    return this.find("customerId", customerId.getValue())
        .stream()
        .map(Mapper::addressModelToDomain)
        .collect(Collectors.toSet());
  }
}
