package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class AddressTest {

  @Test
  void test_AddressValid() {
    final Address address = new Address("Street one");
    assertThat(address)
        .isNotNull()
        .isInstanceOf(Address.class);
    assertThat(address.getValue())
        .isNotBlank();
  }

  @Test
  void test_AddressInvalid() {
    assertThatThrownBy(() -> new Address(""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid address");
  }

}