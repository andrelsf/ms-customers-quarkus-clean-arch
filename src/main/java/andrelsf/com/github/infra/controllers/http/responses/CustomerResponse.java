package andrelsf.com.github.infra.controllers.http.responses;

public record CustomerResponse(
    String customerId,
    String name,
    String email,
    String cellPhone,
    String dateOfBirth,
    IdentificationResponse identification,
    boolean isActive
) {

}
