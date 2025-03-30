package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ActiveTest {

  @Test
  void test_active_valid_true() {
    final Active isActive = assertDoesNotThrow(() -> new Active(Boolean.TRUE));
    assertThat(isActive)
        .isNotNull()
        .isInstanceOf(Active.class);
    assertThat(isActive.getValue())
        .isTrue();
  }

  @Test
  void test_active_valid_false() {
    final Active isActive = assertDoesNotThrow(() -> new Active(Boolean.FALSE));
    assertThat(isActive)
        .isNotNull()
        .isInstanceOf(Active.class);
    assertThat(isActive.getValue())
        .isFalse();
  }

  @Test
  void test_active_invalid() {
    assertThatThrownBy(() -> new Active(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid value Active class cannot have null");
  }
}