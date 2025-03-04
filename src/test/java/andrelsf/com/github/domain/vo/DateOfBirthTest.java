package andrelsf.com.github.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.Period;
import org.junit.jupiter.api.Test;

class DateOfBirthTest {

  @Test
  void test_DateOfBirth_valid() {
    final LocalDate currentDate = LocalDate.now();
    final DateOfBirth dateOfBirthValid = new DateOfBirth(LocalDate.of(1990, 1, 1));
    final int ageExpected = Period.between(currentDate, dateOfBirthValid.getValue())
        .getYears();
    assertThat(dateOfBirthValid)
        .isNotNull()
        .isInstanceOf(DateOfBirth.class);
    assertThat(dateOfBirthValid.getValue())
        .isNotNull();
    assertThat(dateOfBirthValid.getAge())
        .isEqualTo(ageExpected);
  }

  @Test
  void test_DateOfBirth_invalid() {
    assertThatThrownBy(() -> new DateOfBirth(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid date of birth");
  }
}