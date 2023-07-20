package com.tr.domain.entities;

import com.tr.domain.enums.Nickname;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "address_id")
  private Long addressId;

  @Column(name = "postal_code")
  private Integer postalCode;

  @Column(name = "public_place")
  private String publicPlace;

  @Column(name = "new_post_code")
  private Integer newCodePostal;

  @Column(name = "number")
  private Integer number;

  @Column(name = "complement")
  private String complement;

  @Column(name = "neighborhood")
  @Enumerated(EnumType.STRING)
  // @Convert(converter = NicknameConverter.class)
  private Nickname neighborhood;

  @ManyToOne
  @JoinColumn(nullable = false)
  private City city;

  public Address() {}

  public Address(
      Long addressId,
      Integer postalCode,
      Integer newCodePostal,
      String publicPlace,
      Integer number,
      String complement,
      Nickname neighborhood,
      City city) {
    this.addressId = addressId;
    this.postalCode = postalCode;
    this.publicPlace = publicPlace;
    this.number = number;
    this.complement = complement;
    this.neighborhood = neighborhood;
    this.newCodePostal = newCodePostal;
    this.city = city;
  }

  public Address(
      Integer postalCode,
      String publicPlace,
      Integer number,
      String complement,
      Nickname neighborhood,
      Integer newCodePostal,
      City city) {
    this.postalCode = postalCode;
    this.publicPlace = publicPlace;
    this.number = number;
    this.complement = complement;
    this.neighborhood = neighborhood;
    this.newCodePostal = newCodePostal;
    this.city = city;
  }

  public Integer getNewCodePostal() {
    return newCodePostal;
  }

  public void setNewCodePostal(Integer newCodePostal) {
    this.newCodePostal = newCodePostal;
  }

  public Integer getNewPostCode() {
    return newCodePostal;
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

  public Nickname getNeighborhood(Nickname home) {

    return neighborhood;
  }

  public void setNeighborhood(Nickname neighborhood) {

    this.neighborhood = neighborhood;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
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
