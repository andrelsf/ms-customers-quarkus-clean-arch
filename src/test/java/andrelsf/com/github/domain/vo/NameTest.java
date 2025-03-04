package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NameTest {

  @Test
  void test_NameValid_withFirstNameMiddleNameAndLastName() {
    final Name name = new Name("Jose Nome Facil");

    assertThat(name)
        .isNotNull()
        .isInstanceOf(Name.class);
    assertThat(name.getValue())
        .isNotBlank();
  }

  @Test
  void test_NameValid_withFirstNameAndLastName() {
    final Name name = new Name("John Doe");

    assertThat(name)
        .isNotNull()
        .isInstanceOf(Name.class);
    assertThat(name.getValue())
        .isNotBlank();
  }
}