package andrelsf.com.github.infra.controllers.http.requests;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
    @NotBlank String type,
    @NotBlank String address,
    @NotBlank String city,
    @NotBlank String state,
    @NotBlank String country,
    @NotBlank String zipCode
) {

}
