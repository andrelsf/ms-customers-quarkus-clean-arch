package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class StateTest {

  @Test
  void test_StateValid() {
    final State state = new State("SP");
    assertThat(state)
        .isNotNull()
        .isInstanceOf(State.class);
    assertThat(state.getValue())
        .isNotBlank();
  }

  @Test
  void test_StateInvalid() {
    assertThatThrownBy(() -> new State(""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid state");
  }
}