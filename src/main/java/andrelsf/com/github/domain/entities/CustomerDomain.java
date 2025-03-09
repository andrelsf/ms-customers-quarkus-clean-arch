package andrelsf.com.github.domain.entities;

import andrelsf.com.github.domain.vo.Active;
import andrelsf.com.github.domain.vo.CellPhone;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.domain.vo.DateOfBirth;
import andrelsf.com.github.domain.vo.Email;
import andrelsf.com.github.domain.vo.Identification;
import andrelsf.com.github.domain.vo.IdentificationType;
import andrelsf.com.github.domain.vo.Name;
import java.time.LocalDate;

public class CustomerDomain {

  private final CustomUUID id;
  private final Name name;
  private final Email email;
  private final CellPhone cellPhone;
  private final DateOfBirth dateOfBirth;
  private final Identification identification;
  private final Active isActive;

  public CustomerDomain(
      String id,
      String name,
      String email,
      String cellPhone,
      LocalDate dateOfBirth,
      String identificationNumber,
      String identificationNumberType,
      boolean isActive) {
    this.id = new CustomUUID(id);
    this.name = new Name(name);
    this.email = new Email(email);
    this.cellPhone = new CellPhone(cellPhone);
    this.dateOfBirth = new DateOfBirth(dateOfBirth);
    this.identification = new Identification(
        IdentificationType.getTypeBy(identificationNumberType), identificationNumber);
    this.isActive = new Active(isActive);
  }

  public static CustomerDomain create(
      final String name,
      final String email,
      final String cellPhone,
      final LocalDate dateOfBirth,
      final String identificationNumber,
      final String identificationNumberType,
      final boolean isActive
  ) {
    return new CustomerDomain(
        CustomUUID.generate().getValue(),
        name,
        email,
        cellPhone,
        dateOfBirth,
        identificationNumber,
        identificationNumberType,
        isActive);
  }

  public String getId() {
    return this.id.getValue();
  }

  public String getName() {
    return this.name.getValue();
  }

  public String getEmail() {
    return this.email.getValue();
  }

  public String getCellPhone() {
    return this.cellPhone.getValue();
  }

  public LocalDate getDateOfBirth() {
    return this.dateOfBirth.getValue();
  }

  public Identification getIdentification() {
    return this.identification;
  }

  public Active isActive() {
    return this.isActive;
  }
}
