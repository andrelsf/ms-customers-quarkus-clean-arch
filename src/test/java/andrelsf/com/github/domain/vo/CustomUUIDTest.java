package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class CustomUUIDTest {

  @Test
  void test_CustomUUID_valid() {
    final CustomUUID customUUID = CustomUUID.generate();
    assertThat(customUUID)
        .isNotNull()
        .isInstanceOf(CustomUUID.class);
    assertThat(customUUID.getValue())
        .isNotBlank()
        .hasSize(32);
  }

  @Test
  void test_CustomUUID_invalid() {
    final String invalidId = UUID.randomUUID().toString();
    assertThatThrownBy(() -> new CustomUUID(invalidId))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid UUID. id=".concat(invalidId));
  }

}