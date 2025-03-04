package andrelsf.com.github.domain.vo;

public class IdentificationNumber {

  private final String type;
  private final String identificationNumber;

  public IdentificationNumber(final IdentificationNumberType identificationNumberType, final String identificationNumber) {
    identificationNumberType.getValidator().validate(identificationNumber);
    this.type = identificationNumberType.name();
    this.identificationNumber = identificationNumber;
  }

  public String number() {
    return this.identificationNumber;
  }

  public String getType() {
    return this.type;
  }
}
