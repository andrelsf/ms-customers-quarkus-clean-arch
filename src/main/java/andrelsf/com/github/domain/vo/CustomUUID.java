package andrelsf.com.github.domain.vo;

import java.util.UUID;

public class CustomUUID {

  private final String value;

  public CustomUUID(String value) {
    if (value.isBlank() || !value.matches("^[0-9a-f]{32}$")) {
      throw new IllegalArgumentException("Invalid UUID. id=".concat(value));
    }
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static CustomUUID generate() {
    final String[] uuidSplited = UUID.randomUUID()
        .toString()
        .split("-");
    final String uuidCustom = uuidSplited[2]
        .concat(uuidSplited[1])
        .concat(uuidSplited[0])
        .concat(uuidSplited[3])
        .concat(uuidSplited[4]);
    return new CustomUUID(uuidCustom);
  }
}
