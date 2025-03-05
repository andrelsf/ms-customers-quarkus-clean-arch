package andrelsf.com.github.application.usecases;

import andrelsf.com.github.infra.controllers.http.queries.QueryParams;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import java.util.Set;

public interface GetAllCustomers {

  Set<CustomerResponse> execute(QueryParams queryParams);

}
