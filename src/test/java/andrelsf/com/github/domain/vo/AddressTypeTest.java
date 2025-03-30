package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class AddressTypeTest {

  @Test
  void test_addressType_valid() {
    final String addressTypeExpected = "RESIDENTIAL";
    final AddressType residential = assertDoesNotThrow(() ->
        AddressType.getType(addressTypeExpected));
    assertThat(residential)
        .isInstanceOf(AddressType.class)
        .isNotNull();
    assertThat(residential.name())
        .isNotBlank()
        .isEqualTo(addressTypeExpected);
  }

  @Test
  void test_addressType_invalid() {
    final String addressTypeUnknown = "unknown";
    assertThatThrownBy(() -> AddressType.getType(addressTypeUnknown))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("Invalid address type");
  }
}