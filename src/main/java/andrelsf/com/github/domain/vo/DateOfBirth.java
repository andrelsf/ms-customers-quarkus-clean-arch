package andrelsf.com.github.domain.vo;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.time.Period;

public class DateOfBirth {

  private final LocalDate value;

  public DateOfBirth(LocalDate value) {
    if (isNull(value)) {
      throw new IllegalArgumentException("Invalid date of birth");
    }
    this.value = value;
  }

  public LocalDate getValue() {
    return this.value;
  }

  public int getAge() {
    return Period.between(LocalDate.now(), this.value).getYears();
  }
}
