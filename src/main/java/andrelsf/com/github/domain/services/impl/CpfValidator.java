package andrelsf.com.github.domain.services.impl;

import static java.lang.String.valueOf;

import andrelsf.com.github.domain.services.IdentificationService;

public class CpfValidator implements IdentificationService {

  private static final int[] WEIGHT = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

  @Override
  public void validate(final String value) {
    final String cpf = removeFormatting(value);
    if (cpf.length() != 11) {
      throw new IllegalArgumentException("Invalid length for CPF=".concat(cpf));
    }
    if (allEqual(cpf)) {
      throw new IllegalArgumentException("Invalid CPF all numbers are the same. ".concat(cpf));
    }
    final String cpfBase = getCpfBase(cpf);
    final int firstVerifierDigitProvided = getFirstVerifierDigits(cpf);
    final int firstVerifierDigitCalculated = calculateDigit(cpfBase);
    if (firstVerifierDigitProvided != firstVerifierDigitCalculated) {
      throw new IllegalArgumentException(
          "Invalid first verifier digit. dv=".concat(valueOf(firstVerifierDigitCalculated)));
    }
    final int secondVerifierDigitProvided = getSecondVerifierDigits(cpf);
    final int secondVerifierDigitCalculated = calculateDigit(cpfBase.concat(valueOf(firstVerifierDigitCalculated)));
    if (secondVerifierDigitCalculated != secondVerifierDigitProvided) {
      throw new IllegalArgumentException(
          "Invalid second verifier digit. dv=".concat(valueOf(secondVerifierDigitCalculated)));
    }
  }

  private int calculateDigit(final String cpfBase) {
    int sum = 0;
    for (int count = cpfBase.length() - 1, digit; count >= 0; count--) {
      digit = Integer.parseInt(cpfBase.substring(count, count + 1));
      sum += digit * WEIGHT[WEIGHT.length - cpfBase.length() + count];
    }
    sum = 11 - sum % 11;
    return sum > 9 ? 0 : sum;
  }

  private int getSecondVerifierDigits(final String cpf) {
    return Integer.parseInt(cpf.substring(10, 11));
  }

  private int getFirstVerifierDigits(final String cpf) {
    return Integer.parseInt(cpf.substring(9, 10));
  }

  private String getCpfBase(final String cpf) {
    return cpf.substring(0, 9);
  }

  private boolean allEqual(final String cpf) {
    return cpf.matches("(\\d)\\1{10}");
  }
}
