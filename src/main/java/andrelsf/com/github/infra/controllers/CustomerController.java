package andrelsf.com.github.infra.controllers;

import andrelsf.com.github.application.usecases.DeleteCustomer;
import andrelsf.com.github.application.usecases.GetAddresses;
import andrelsf.com.github.application.usecases.GetAllCustomers;
import andrelsf.com.github.application.usecases.GetCustomer;
import andrelsf.com.github.application.usecases.RegistryAddress;
import andrelsf.com.github.application.usecases.RegistryCustomer;
import andrelsf.com.github.application.usecases.UpdateCustomer;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.infra.controllers.http.queries.QueryParams;
import andrelsf.com.github.infra.controllers.http.requests.AddressRequest;
import andrelsf.com.github.infra.controllers.http.requests.CustomerRequest;
import andrelsf.com.github.infra.controllers.http.responses.AddressResponse;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.Set;

@Path("/v1/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {

  @Inject GetCustomer getCustomer;
  @Inject DeleteCustomer deleteCustomer;
  @Inject UpdateCustomer updateCustomer;
  @Inject GetAllCustomers getAllCustomers;
  @Inject RegistryCustomer registryCustomer;
  @Inject GetAddresses getAddresses;
  @Inject RegistryAddress registryAddress;

  @GET
  public Response getAllCustomers(
      @QueryParam("page") @DefaultValue("0") final Integer page,
      @QueryParam("size") @DefaultValue("10") final Integer size) {
    final QueryParams queryParams = QueryParams.create(page, size);
    final Set<CustomerResponse> customers = getAllCustomers.execute(queryParams);
    return Response.ok(customers).build();
  }

  @POST
  public Response postCustomer(@Valid final CustomerRequest customerRequest) {
    final String customerId = registryCustomer.execute(customerRequest);
    return Response.created(URI.create(customerId)).build();
  }

  @GET
  @Path("/{customerId}")
  public Response getCustomer(@PathParam("customerId") @NotNull final CustomUUID customerId) {
    final CustomerResponse customerResponse = getCustomer.execute(customerId);
    return Response.ok(customerResponse).build();
  }

  @PUT
  @Path("/{customerId}")
  public Response putCustomer(
      @PathParam("customerId") @NotNull final CustomUUID customerId,
      @Valid final CustomerRequest customerRequest) {
    updateCustomer.execute(customerId, customerRequest);
    return Response.noContent().build();
  }

  @DELETE
  @Path("/{customerId}")
  public Response deleteCustomer(@PathParam("customerId") @NotNull final CustomUUID customerId) {
    deleteCustomer.execute(customerId);
    return Response.noContent().build();
  }

  @GET
  @Path("/{customerId}/addresses")
  public Response getAddresses(@PathParam("customerId") @NotNull final CustomUUID customerId) {
    final Set<AddressResponse> addresses = getAddresses.execute(customerId);
    return Response.ok(addresses).build();
  }

  @POST
  @Path("/{customerId}/addresses")
  public Response postAddress(
      @PathParam("customerId") @NotNull final CustomUUID customerId,
      @Valid final AddressRequest addressRequest) {
    final CustomUUID addressId = registryAddress.execute(customerId, addressRequest);
    return Response.created(URI.create(addressId.getValue())).build();
  }
}
