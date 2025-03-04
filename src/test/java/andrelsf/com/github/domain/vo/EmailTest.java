package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EmailTest {

  @Test
  void test_Email_valid() {
    final String validEmail = "test@test.com";
    final Email email = new Email(validEmail);

    assertThat(email)
        .isNotNull()
        .isInstanceOf(Email.class);
    assertThat(email.getValue())
        .isNotBlank()
        .isEqualTo(validEmail);
  }

}