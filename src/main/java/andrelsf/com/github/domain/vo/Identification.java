package andrelsf.com.github.domain.vo;

public class Identification {

  private final String type;
  private final String number;

  public Identification(final IdentificationType identificationType, final String number) {
    identificationType.getValidator().validate(number);
    this.type = identificationType.name();
    this.number = number;
  }

  public String getNumber() {
    return this.number;
  }

  public String getType() {
    return this.type;
  }
}
