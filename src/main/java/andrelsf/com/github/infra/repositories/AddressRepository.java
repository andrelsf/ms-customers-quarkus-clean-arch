package andrelsf.com.github.infra.repositories;

import andrelsf.com.github.domain.entities.AddressDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import java.util.Set;

public interface AddressRepository {

  CustomUUID save(AddressDomain addressDomain);

  Set<AddressDomain> getAll(CustomUUID customerId);
}
