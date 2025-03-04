package andrelsf.com.github.domain.vo;

import andrelsf.com.github.domain.services.IdentificationNumberService;
import andrelsf.com.github.domain.services.impl.CnpjValidator;
import andrelsf.com.github.domain.services.impl.CpfValidator;
import java.util.Arrays;

public enum IdentificationNumberType {
  CPF(new CpfValidator()),
  CNPJ(new CnpjValidator());

  private final IdentificationNumberService identificationNumberService;

  IdentificationNumberType(IdentificationNumberService identificationNumberService) {
    this.identificationNumberService = identificationNumberService;
  }

  public IdentificationNumberService getValidator() {
    return this.identificationNumberService;
  }

  public static IdentificationNumberType getTypeBy(final String value) {
    return Arrays.stream(IdentificationNumberType.values())
        .filter(identificationNumberType -> identificationNumberType.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() ->
            new RuntimeException("Invalid Identification Number type. ".concat(value)));
  }
}
