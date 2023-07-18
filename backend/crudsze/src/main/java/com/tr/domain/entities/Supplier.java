package com.tr.domain.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "supplier")
public class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "supllier_id")
  private Long supplierId;

  @Column(name = "name")
  private String name;

  @Column(name = "date_registration")
  private Date dateRegistration;

  @Column(name = "corparete_name")
  private String corpareteName;

  @Column(name = "fantasy_name")
  private String fantasyName;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "responsable")
  private String responsable;

  @Column(name = "email")
  private String email;

  @Column(name = "municipal_registration")
  private String municipalRegistration;

  @Column(name = "phone")
  private String phone;

  @Column(name = "celphone")
  private String celphone;

  @Column(name = "date_of_lastPurchase")
  private Date dateOfLastPurchase;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Address address;

  public Supplier() {}

  public Supplier(
      Long supplierId,
      Date dateRegistration,
      Date dateOfLastPurchase,
      String cnpj,
      String corpareteName,
      String fantasyName,
      String responsable,
      String email,
      String municipalRegistration,
      String phone,
      String celphone,
      Address address) {
       this.supplierId = supplierId;
       this.dateRegistration = dateRegistration;
       this.dateOfLastPurchase = dateOfLastPurchase;
       this.cnpj = cnpj;
       this.corpareteName = corpareteName;
       this.fantasyName = fantasyName;
       this.responsable = responsable;
       this.email = email;
       this.municipalRegistration = municipalRegistration;
       this.phone = phone;
       this.celphone = celphone;
       this.address = address;
  }
  
    public Supplier(
      Date dateRegistration,
      Date dateOfLastPurchase,
      String cnpj,
      String corpareteName,
      String fantasyName,
      String responsable,
      String email,
      String municipalRegistration,
      String phone,
      String celphone,
      Address address) {
       this.dateRegistration = dateRegistration;
       this.dateOfLastPurchase = dateOfLastPurchase;
       this.cnpj = cnpj;
       this.corpareteName = corpareteName;
       this.fantasyName = fantasyName;
       this.responsable = responsable;
       this.email = email;
       this.municipalRegistration = municipalRegistration;
       this.phone = phone;
       this.celphone = celphone;
       this.address = address;
  }

  public Long getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(Long supplierId) {
    this.supplierId = supplierId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateRegistration() {
    return dateRegistration;
  }

  public void setDateRegistration(Date dateRegistration) {
    this.dateRegistration = dateRegistration;
  }

  public String getCorpareteName() {
    return corpareteName;
  }

  public void setCorpareteName(String corpareteName) {
    this.corpareteName = corpareteName;
  }

  public String getFantasyName() {
    return fantasyName;
  }

  public void setFantasyName(String fantasyName) {
    this.fantasyName = fantasyName;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getResponsable() {
    return responsable;
  }

  public void setResponsable(String responsable) {
    this.responsable = responsable;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMunicipalRegistration() {
    return municipalRegistration;
  }

  public void setMunicipalRegistration(String municipalRegistration) {
    this.municipalRegistration = municipalRegistration;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCelphone() {
    return celphone;
  }

  public void setCelphone(String celphone) {
    this.celphone = celphone;
  }

  public Date getDateOfLastPurchase() {
    return dateOfLastPurchase;
  }

  public void setDateOfLastPurchase(Date dateOfLastPurchase) {
    this.dateOfLastPurchase = dateOfLastPurchase;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Supplier))
      return false;
    Supplier supplier = (Supplier) o;
    return getSupplierId().equals(supplier.getSupplierId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSupplierId());
  }
}
