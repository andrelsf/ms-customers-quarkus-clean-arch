package andrelsf.com.github.infra.controllers;

import andrelsf.com.github.application.usecases.GetAllCustomers;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Set;

@Path("/v1/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {

  @Inject
  GetAllCustomers getAllCustomers;

  @GET
  public Response getAll() {
    final Set<CustomerResponse> customers = getAllCustomers.execute();
    return Response.ok(customers).build();
  }
}
