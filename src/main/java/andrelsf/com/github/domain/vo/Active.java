package andrelsf.com.github.domain.vo;

import static java.util.Objects.isNull;

public class Active {

  private final boolean value;

  public Active(Boolean value) {
    if (isNull(value)) {
      throw new IllegalArgumentException("Invalid value Active class cannot have null");
    }
    this.value = value;
  }

  public boolean getValue() {
    return this.value;
  }
}
