package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private Long addressId;

  @Column(name = "postal_code")
  private Integer postalCode;

  @Column(name = "public_place")
  private String publicPlace;

  @Column(name = "number")
  private Integer number;

  @Column(name = "complement")
  private String complement;

  @Column(name = "neighborhood")
  private String neighborhood;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "city")
  private City city;

  public Address() {}

  public Address(
      Long addressId,
      Integer postalCode,
      String publicPlace,
      Integer number,
      String complement,
      String neighborhood) {
    this.addressId = addressId;
    this.postalCode = postalCode;
    this.publicPlace = publicPlace;
    this.number = number;
    this.complement = complement;
    this.neighborhood = neighborhood;
  }

  public Address(
      Integer postalCode,
      String publicPlace,
      Integer number,
      String complement,
      String neighborhood) {
    this.postalCode = postalCode;
    this.publicPlace = publicPlace;
    this.number = number;
    this.complement = complement;
    this.neighborhood = neighborhood;
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public Integer getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(Integer postalCode) {
    this.postalCode = postalCode;
  }

  public String getPublicPlace() {
    return publicPlace;
  }

  public void setPublicPlace(String publicPlace) {
    this.publicPlace = publicPlace;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(String complement) {
    this.complement = complement;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Address)) return false;
    Address address = (Address) o;
    return getAddressId().equals(address.getAddressId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAddressId());
  }
}
