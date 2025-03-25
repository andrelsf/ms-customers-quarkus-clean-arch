package andrelsf.com.github.domain.vo;

import andrelsf.com.github.domain.services.IdentificationService;
import andrelsf.com.github.domain.services.impl.CnpjValidator;
import andrelsf.com.github.domain.services.impl.CpfValidator;
import java.util.Arrays;

public enum IdentificationType {
  CPF(new CpfValidator()),
  CNPJ(new CnpjValidator());

  private final IdentificationService identificationService;

  IdentificationType(IdentificationService identificationService) {
    this.identificationService = identificationService;
  }

  public IdentificationService getValidator() {
    return this.identificationService;
  }

  public static IdentificationType getTypeBy(final String value) {
    return Arrays.stream(IdentificationType.values())
        .filter(identificationNumberType -> identificationNumberType.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Invalid Identification type."));
  }
}
