package andrelsf.com.github.application.usecases;

import andrelsf.com.github.domain.vo.CustomUUID;

public interface DeleteCustomer {

  void execute(CustomUUID customerId);
}
