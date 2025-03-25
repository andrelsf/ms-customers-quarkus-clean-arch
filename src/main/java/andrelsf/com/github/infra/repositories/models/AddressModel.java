package andrelsf.com.github.infra.repositories.models;

import andrelsf.com.github.domain.vo.AddressType;
import andrelsf.com.github.domain.vo.CustomUUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "addresses")
public class AddressModel {

  @Id
  @Column(name = "address_id", length = 32, nullable = false)
  private String id;

  @Column(name = "customer_id", length = 32, nullable = false)
  private String customerId;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", length = 11, nullable = false)
  private AddressType type;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "city", length = 60, nullable = false)
  private String city;

  @Column(name = "state", length = 120, nullable = false)
  private String state;

  @Column(name = "country", length = 120, nullable = false)
  private String country;

  @Column(name = "zip_code", length = 60, nullable = false)
  private String zipCode;

  public AddressModel() {
  }

  public AddressModel(
      String id,
      String customerId,
      AddressType type,
      String address,
      String city,
      String state,
      String country,
      String zipCode) {
    this.id = id;
    this.customerId = customerId;
    this.type = type;
    this.address = address;
    this.city = city;
    this.state = state;
    this.country = country;
    this.zipCode = zipCode;
  }

  public static AddressModel create(
      String customerId,
      AddressType type,
      String address,
      String city,
      String state,
      String country,
      String zipCode) {
    return new AddressModel(
        CustomUUID.generate().getValue(),
        customerId,
        type,
        address,
        city,
        state,
        country,
        zipCode);
  }

  public String getId() {
    return id;
  }

  public String getCustomerId() {
    return customerId;
  }

  public AddressType getType() {
    return type;
  }

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getZipCode() {
    return zipCode;
  }
}
