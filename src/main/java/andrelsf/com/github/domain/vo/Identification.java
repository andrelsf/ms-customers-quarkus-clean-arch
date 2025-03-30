package andrelsf.com.github.domain.vo;

import static java.util.Objects.isNull;

import org.apache.commons.lang3.StringUtils;

public class Identification {

  private final String type;
  private final String number;

  public Identification(final IdentificationType identificationType, final String number) {
    if (isNull(identificationType) || number.isBlank()) {
      throw new IllegalArgumentException("Invalid identification or number");
    }
    identificationType.getValidator().validate(number);
    this.type = identificationType.name();
    this.number = number;
  }

  public String getNumber() {
    if (StringUtils.isNotBlank(this.number)) {
      return this.number;
    }
    throw new IllegalArgumentException("Invalid value number.");
  }

  public String getType() {
    if (StringUtils.isNotBlank(this.type)) {
      return this.type;
    }
    throw new IllegalArgumentException("Invalid value type.");
  }
}
