package andrelsf.com.github.domain.services;

public interface IdentificationService {

  String ZERO_VALUE_REGEX = "^[0]+$";
  String CHARACTERES_CNPJ_REGEX = "[./-]";

  void validate(final String value);

  default String removeFormatting(final String value) {
    return value.trim().replaceAll(CHARACTERES_CNPJ_REGEX, "");
  }
}
