package andrelsf.com.github.infra.controllers.http.responses;

public record AddressResponse(
    String id,
    String customerId,
    String type,
    String address,
    String city,
    String state,
    String country,
    String zipCode
) {

}
