package andrelsf.com.github.domain.vo;

public class State {

  private final String value;

  public State(String state) {
    if (state.isBlank()) {
      throw new IllegalArgumentException("Invalid state");
    }
    this.value = state;
  }

  public String getValue() {
    return this.value;
  }
}
