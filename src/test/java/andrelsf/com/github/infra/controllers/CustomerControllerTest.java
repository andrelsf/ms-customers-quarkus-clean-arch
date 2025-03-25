package andrelsf.com.github.infra.controllers;

import static andrelsf.com.github.application.utils.Mapper.addressDomainToResponse;
import static andrelsf.com.github.application.utils.Mapper.addressRequestToDomain;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import andrelsf.com.github.application.usecases.DeleteCustomer;
import andrelsf.com.github.application.usecases.GetAddresses;
import andrelsf.com.github.application.usecases.GetAllCustomers;
import andrelsf.com.github.application.usecases.GetCustomer;
import andrelsf.com.github.application.usecases.RegistryAddress;
import andrelsf.com.github.application.usecases.RegistryCustomer;
import andrelsf.com.github.application.usecases.UpdateCustomer;
import andrelsf.com.github.application.utils.Mapper;
import andrelsf.com.github.domain.entities.AddressDomain;
import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.domain.vo.IdentificationType;
import andrelsf.com.github.infra.controllers.http.queries.QueryParams;
import andrelsf.com.github.infra.controllers.http.requests.AddressRequest;
import andrelsf.com.github.infra.controllers.http.requests.CustomerRequest;
import andrelsf.com.github.infra.controllers.http.requests.IdentificationRequest;
import andrelsf.com.github.infra.controllers.http.responses.AddressResponse;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import andrelsf.com.github.infra.controllers.http.responses.IdentificationResponse;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CustomerControllerTest {

  @InjectMock
  GetCustomer getCustomer;
  @InjectMock
  DeleteCustomer deleteCustomer;
  @InjectMock
  UpdateCustomer updateCustomer;
  @InjectMock
  GetAllCustomers getAllCustomers;
  @InjectMock
  RegistryCustomer registryCustomer;
  @InjectMock
  RegistryAddress registryAddress;
  @InjectMock
  GetAddresses getAddresses;

  @Inject
  CustomerController customerController;

  private CustomerResponse johnDoe;
  private CustomerResponse janeDoe;
  private CustomerRequest joseNomeFacil;

  @BeforeEach
  void setUp() {
    johnDoe = new CustomerResponse(
        CustomUUID.generate().getValue(),
        "John Doe",
        "john.doe@test.com",
        "+5562911223344",
        LocalDate.of(1990, 1, 1).toString(),
        new IdentificationResponse("27995081030", IdentificationType.CNPJ.name()),
        Boolean.TRUE);
    janeDoe = new CustomerResponse(
        CustomUUID.generate().getValue(),
        "Jane Doe",
        "Jane.doe@test.com",
        "+5562944332211",
        LocalDate.of(1990, 1, 1).toString(),
        new IdentificationResponse("10647269090", IdentificationType.CNPJ.name()),
        Boolean.TRUE);
    joseNomeFacil = new CustomerRequest(
        "Jose Nome Facil",
        "jose.facil@test.com",
        "+5562911223344",
        LocalDate.of(1990, 1, 1),
        new IdentificationRequest(IdentificationType.CPF.name(), "490.494.270-14"),
        Boolean.TRUE);
  }

  @Test
  void test_getAllCustomers() {
    final QueryParams queryParams = new QueryParams(0, 10);
    Set<CustomerResponse> customerResponses = Set.of(johnDoe, janeDoe);
    when(getAllCustomers.execute(queryParams))
        .thenReturn(customerResponses);

    final Response response = customerController.getAllCustomers(0, 10);

    assertThat(response)
        .isNotNull()
        .isInstanceOf(Response.class);
    assertThat(response.getStatus())
        .isEqualTo(StatusCode.OK);
    assertNotNull(response.getEntity());
    final Set<CustomerResponse> entities = (Set<CustomerResponse>) response.getEntity();
    assertThat(entities)
        .isInstanceOf(Set.class)
        .isNotEmpty()
        .hasSize(2);
    assertThat(entities.containsAll(customerResponses))
        .isTrue();
  }

  @Test
  void test_postCustomer() {
    final String customerId = CustomUUID.generate().getValue();
    when(registryCustomer.execute(joseNomeFacil))
        .thenReturn(customerId);

    final Response response = customerController.postCustomer(joseNomeFacil);

    assertThat(response)
        .isNotNull()
        .isInstanceOf(Response.class);
    assertThat(response.getStatus())
        .isEqualTo(StatusCode.CREATED);
    assertThat(response.getEntity())
        .isNull();
    assertThat(response.getLocation()
        .getPath()
        .contains(customerId))
        .isTrue();
  }

  @Test
  void test_getCustomer() {
    final CustomerDomain customerDomain = Mapper.requestToDomain(joseNomeFacil);
    final CustomerResponse customerResponse = Mapper.domainToResponse(customerDomain);

    final CustomUUID customerId = new CustomUUID(customerDomain.getId());
    when(getCustomer.execute(customerId))
        .thenReturn(customerResponse);

    final Response response = customerController.getCustomer(customerId);

    assertThat(response)
        .isNotNull()
        .isInstanceOf(Response.class);
    assertThat(response.getStatus())
        .isEqualTo(StatusCode.OK);
    assertThat(response.getEntity())
        .isNotNull();
    final CustomerResponse responseEntity = (CustomerResponse) response.getEntity();
    assertThat(responseEntity)
        .isNotNull()
        .isInstanceOf(CustomerResponse.class);
    assertThat(responseEntity.customerId())
        .isNotBlank()
        .isInstanceOf(String.class)
        .isEqualTo(customerId.getValue());
  }

  @Test
  void test_putCustomer() {
    final CustomUUID customerId = CustomUUID.generate();
    doNothing()
        .when(updateCustomer).execute(customerId, joseNomeFacil);

    final Response response = customerController.putCustomer(customerId, joseNomeFacil);

    assertThat(response)
        .isNotNull()
        .isInstanceOf(Response.class);
    assertThat(response.getStatus())
        .isEqualTo(StatusCode.NO_CONTENT);
    assertThat(response.getEntity())
        .isNull();
    verify(updateCustomer, times(1))
        .execute(customerId, joseNomeFacil);
  }

  @Test
  void test_deleteCustomer() {
    final CustomUUID customerId = CustomUUID.generate();
    doNothing()
        .when(deleteCustomer).execute(customerId);

    final Response response = customerController.deleteCustomer(customerId);

    assertThat(response)
        .isNotNull()
        .isInstanceOf(Response.class);
    assertThat(response.getStatus())
        .isEqualTo(StatusCode.NO_CONTENT);
    assertThat(response.getEntity())
        .isNull();
    verify(deleteCustomer, times(1)).execute(customerId);
  }

  @Test
  void test_postAddress_success() {
    final CustomUUID customerId = CustomUUID.generate();
    final AddressRequest addressRequest = buildAddressRequest();
    when(registryAddress.execute(customerId, addressRequest))
        .thenReturn(CustomUUID.generate());

    final Response response = customerController.postAddress(customerId, addressRequest);

    assertThat(response)
        .isNotNull()
        .isInstanceOf(Response.class);
    assertThat(response.getStatus())
        .isEqualTo(StatusCode.CREATED);
    assertThat(response.getEntity())
        .isNull();
  }

  @Test
  void test_getAddresses_success() {
    final CustomUUID customerId = CustomUUID.generate();
    final AddressRequest addressRequest = buildAddressRequest();
    final AddressDomain addresses = addressRequestToDomain(customerId, addressRequest);
    when(getAddresses.execute(customerId))
        .thenReturn(Set.of(addressDomainToResponse(addresses)));

    final Response response = customerController.getAddresses(customerId);

    assertThat(response)
        .isNotNull()
        .isInstanceOf(Response.class);
    assertThat(response.getStatus())
        .isEqualTo(StatusCode.OK);
    assertThat(response.getEntity())
        .isNotNull()
        .isInstanceOf(Set.class);
    final Set<AddressResponse> addressEntitiesResponse = (Set<AddressResponse>) response.getEntity();
    assertThat(addressEntitiesResponse)
        .isNotEmpty()
        .hasSize(1);
  }

  private AddressRequest buildAddressRequest() {
    return new AddressRequest(
        "RESIDENTIAL",
        "Street test",
        "Test",
        "TS",
        "Brazil",
        "79045300");
  }
}