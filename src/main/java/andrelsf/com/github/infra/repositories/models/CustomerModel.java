package andrelsf.com.github.infra.repositories.models;

import andrelsf.com.github.domain.vo.CustomUUID;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "customers")
public class CustomerModel extends PanacheEntityBase {

  @Id
  @Column(name = "customer_id", length = 32, nullable = false)
  private String customerId;

  @Column(name = "name", length = 180, nullable = false)
  private String name;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "cell_phone", length = 18, nullable = false, unique = true)
  private String cellPhone;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "identification_type", length = 12, nullable = false)
  private String identificationType;

  @Column(name = "identification_number", length = 60, nullable = false)
  private String identificationNumber;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  public CustomerModel() {
  }

  public CustomerModel(
      String customerId,
      String name,
      String email,
      String cellPhone,
      LocalDate dateOfBirth,
      String identificationType,
      String identificationNumber) {
    this.customerId = customerId;
    this.name = name;
    this.email = email;
    this.cellPhone = cellPhone;
    this.dateOfBirth = dateOfBirth;
    this.identificationType = identificationType;
    this.identificationNumber = identificationNumber;
  }

  public static CustomerModel create(
      String name,
      String email,
      String cellPhone,
      LocalDate dateOfBirth,
      String identificationType,
      String identificationNumber) {
    return new CustomerModel(
        CustomUUID.generate().getValue(),
        name,
        email,
        cellPhone,
        dateOfBirth,
        identificationType,
        identificationNumber);
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getCellPhone() {
    return cellPhone;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public String getIdentificationType() {
    return identificationType;
  }

  public String getIdentificationNumber() {
    return identificationNumber;
  }
}
