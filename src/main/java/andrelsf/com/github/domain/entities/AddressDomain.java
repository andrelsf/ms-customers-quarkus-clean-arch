package andrelsf.com.github.domain.entities;

import andrelsf.com.github.domain.vo.Address;
import andrelsf.com.github.domain.vo.AddressType;
import andrelsf.com.github.domain.vo.City;
import andrelsf.com.github.domain.vo.Country;
import andrelsf.com.github.domain.vo.CustomUUID;
import andrelsf.com.github.domain.vo.State;
import andrelsf.com.github.domain.vo.ZipCode;

public class AddressDomain {

  private final CustomUUID id;
  private final CustomUUID customerId;
  private final AddressType type;
  private final Address address;
  private final City city;
  private final State state;
  private final Country country;
  private final ZipCode zipCode;

  public AddressDomain(
      String id,
      String customerId,
      String type,
      String address,
      String city,
      String state,
      String country,
      String zipCode) {
    this.id = new CustomUUID(id);
    this.customerId = new CustomUUID(customerId);
    this.type = new AddressType(type);
    this.address = new Address(address);
    this.city = new City(city);
    this.state = new State(state);
    this.country = new Country(country);
    this.zipCode = new ZipCode(zipCode);
  }

  public static AddressDomain create(
      String customerId,
      String type,
      String address,
      String city,
      String state,
      String country,
      String zipCode) {
    return new AddressDomain(
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
    return this.id.getValue();
  }

  public String getCustomerId() {
    return this.customerId.getValue();
  }

  public String getType() {
    return this.type.getValue();
  }

  public String getAddress() {
    return this.address.getValue();
  }

  public String getCity() {
    return this.city.getValue();
  }

  public String getState() {
    return this.state.getValue();
  }

  public String getCountry() {
    return this.country.getValue();
  }

  public String getZipCode() {
    return this.zipCode.getValue();
  }
}
