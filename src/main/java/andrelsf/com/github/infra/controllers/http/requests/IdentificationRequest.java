package andrelsf.com.github.infra.controllers.http.requests;

import jakarta.validation.constraints.NotEmpty;

public record IdentificationRequest(
    @NotEmpty String type,
    @NotEmpty String number) {

}
