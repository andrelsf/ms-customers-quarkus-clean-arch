package andrelsf.com.github.application.usecases.impl;

import static andrelsf.com.github.application.utils.Mapper.requestToDomain;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import andrelsf.com.github.domain.entities.CustomerDomain;
import andrelsf.com.github.domain.vo.IdentificationType;
import andrelsf.com.github.infra.controllers.http.queries.QueryParams;
import andrelsf.com.github.infra.controllers.http.requests.CustomerRequest;
import andrelsf.com.github.infra.controllers.http.requests.IdentificationRequest;
import andrelsf.com.github.infra.controllers.http.responses.CustomerResponse;
import andrelsf.com.github.infra.repositories.CustomerRepository;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllCustomersUseCaseTest {

  @Mock
  private CustomerRepository customerRepository;
  @InjectMocks
  private GetAllCustomersUseCase getAllCustomersUseCase;

  private CustomerRequest johnDoe;
  private CustomerRequest janeDoe;
  private CustomerRequest joseNomeFacil;

  @BeforeEach
  void setUp() {
    johnDoe = new CustomerRequest(
        "John Doe",
        "john.doe@test.com",
        "+5562911223344",
        LocalDate.of(1990, 1, 1),
        new IdentificationRequest(IdentificationType.CPF.name(), "27995081030"),
        Boolean.TRUE);
    janeDoe = new CustomerRequest(
        "Jane Doe",
        "Jane.doe@test.com",
        "+5562944332211",
        LocalDate.of(1990, 1, 1),
        new IdentificationRequest(IdentificationType.CPF.name(), "10647269090"),
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
  void test_should_get_a_list_of_customers() {
    final QueryParams queryParams = new QueryParams(0, 10);
    final CustomerDomain joseNomeFacilDomain = requestToDomain(joseNomeFacil);
    final CustomerDomain johnDoeDomain = requestToDomain(johnDoe);
    final CustomerDomain janeDoeDomain = requestToDomain(janeDoe);

    when(customerRepository.getAll(queryParams))
        .thenReturn(Set.of(johnDoeDomain, janeDoeDomain, joseNomeFacilDomain));

    final Set<CustomerResponse> customers = getAllCustomersUseCase.execute(queryParams);

    assertThat(customers)
        .isInstanceOf(Set.class)
        .isNotEmpty();
  }
}