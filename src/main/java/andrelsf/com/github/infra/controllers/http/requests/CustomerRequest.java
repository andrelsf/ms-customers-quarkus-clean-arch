package andrelsf.com.github.infra.controllers.http.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CustomerRequest(
    @NotEmpty String name,
    @NotEmpty @Email String email,
    @NotEmpty String cellPhone,
    @NotNull LocalDate dateOfBirth,
    @NotNull IdentificationRequest identification
) {

}
