package andrelsf.com.github.domain.vo;

import java.util.Arrays;

public enum AddressType {
  RESIDENTIAL, OFFICE, OTHER;

  public static AddressType getType(final String value) {
    return Arrays.stream(AddressType.values())
        .filter(addressType -> addressType.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Invalid address type"));
  }
}
