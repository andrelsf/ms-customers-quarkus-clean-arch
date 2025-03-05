package andrelsf.com.github.domain.services.impl;

import static java.lang.String.valueOf;

import andrelsf.com.github.domain.services.IdentificationService;

public class CnpjValidator implements IdentificationService {

  private static final String FORMATTING_DV_REGEX = "[\\d]{2}";
  private static final String BASE_FORMATTING_CNPJ_REGEX = "[A-Z\\d]{12}";
  private static final int[] VERIFIER_DIGIT_WEIGHTS = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

  @Override
  public void validate(final String cnpj) {
    final String cnpjUnformatted = removeFormatting(cnpj);
    isCnpjValidFormatWithVerifierDigit(cnpjUnformatted);
    verifyVerifierDigits(cnpjUnformatted);
  }

  private void verifyVerifierDigits(final String cnpj) {
    final String baseCnpj = getBaseCnpj(cnpj);
    final String verifierDigitsProvided = getVerifierDigits(cnpj);
    calculateFirstVerifierDigit(baseCnpj, verifierDigitsProvided);
    calculateSecondVerifierDigit(baseCnpj, verifierDigitsProvided);
  }

  private void calculateSecondVerifierDigit(final String baseCnpj, final String verifierDigitsProvided) {
    final String firstVerifierDigit = getFirstVerifierDigit(verifierDigitsProvided);
    final String secondVerifierDigit = getSecondVerifierDigit(verifierDigitsProvided);
    int result = calculateVerifierDigit(baseCnpj.concat(firstVerifierDigit));
    final String secondVerifierDigitCalculated = getVerifyDigit(result);
    if (!secondVerifierDigit.equals(secondVerifierDigitCalculated)) {
      throw new IllegalArgumentException(
          "Invalid second verifier digit. dv=".concat(secondVerifierDigit));
    }
  }

  private void calculateFirstVerifierDigit(final String baseCnpj, final String verifierDigitsProvided) {
    final String firstVerifierDigit = getFirstVerifierDigit(verifierDigitsProvided);
    int result = calculateVerifierDigit(baseCnpj);
    final String firstVerifierDigitCalculated = getVerifyDigit(result);
    if (!firstVerifierDigit.equals(firstVerifierDigitCalculated)) {
      throw new IllegalArgumentException(
          "Invalid first verifier digit. dv=".concat(firstVerifierDigit));
    }
  }

  private String getVerifierDigits(final String cnpj) {
    return cnpj.substring(12);
  }

  private String getVerifyDigit(final int sum) {
    final int divisionModule = 11;
    if ((sum % divisionModule) < 2) {
      return "0";
    }
    int result = divisionModule - (sum % divisionModule);
    return valueOf(result);
  }

  private int calculateVerifierDigit(final String baseCnpj) {
    int sum = 0;
    int baseValue = '0';
    int baseCnpjLength = baseCnpj.length();
    for (int indice = baseCnpjLength - 1; indice >= 0; indice--) {
      int charValue = (int) baseCnpj.charAt(indice) - baseValue;
      sum += charValue * VERIFIER_DIGIT_WEIGHTS[VERIFIER_DIGIT_WEIGHTS.length - baseCnpjLength + indice];
    }
    return sum;
  }

  private void isCnpjValidFormatWithVerifierDigit(final String cnpj) {
    if (cnpj.length() != 14) {
      throw new IllegalArgumentException("Invalid length for CNPJ. ".concat(cnpj));
    }
    boolean isValid = cnpj.matches(BASE_FORMATTING_CNPJ_REGEX.concat(FORMATTING_DV_REGEX))
        && !cnpj.matches(ZERO_VALUE_REGEX);
    if (!isValid) {
      throw new IllegalArgumentException("Invalid formatting ".concat(cnpj));
    }
  }

  private String getBaseCnpj(final String cnpj) {
    return cnpj.substring(0, 12);
  }

  private String getFirstVerifierDigit(final String verifierDigitsProvided) {
    return verifierDigitsProvided.substring(0, 1);
  }

  private String getSecondVerifierDigit(final String verifierDigitsProvided) {
    return verifierDigitsProvided.substring(1);
  }
}
