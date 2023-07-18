package com.tr.domain.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "company_id")
  private Long companyId;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "celphone")
  private String celphone;

  @Column(name = "date_registration")
  private Date dateRegistration;

  @Column(name = "logo")
  private String logo;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "state_register")
  private String stateRegister;

  @Column(name = "municiapl_register")
  private String municipalRegister;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Address address;

  public Company(
      Long companyId,
      String name,
      String email,
      String phone,
      String celphone,
      Date dateRegistration,
      String logo,
      String cnpj,
      String stateRegister,
      String municipalRegister,
      Address address) {
    this.companyId = companyId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.celphone = celphone;
    this.dateRegistration = dateRegistration;
    this.logo = logo;
    this.cnpj = cnpj;
    this.stateRegister = stateRegister;
    this.address = address;
  }

  public Company(
      String name,
      String email,
      String phone,
      String celphone,
      Date dateRegistration,
      String logo,
      String cnpj,
      String stateRegister,
      String municipalRegister,
      Address address) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.celphone = celphone;
    this.dateRegistration = dateRegistration;
    this.logo = logo;
    this.cnpj = cnpj;
    this.stateRegister = stateRegister;
    this.address = address;
  }

  public Company() {}

  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public Date getDateRegistration() {
    return dateRegistration;
  }

  public void setDateRegistration(Date dateRegistration) {
    this.dateRegistration = dateRegistration;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getStateRegister() {
    return stateRegister;
  }

  public void setStateRegister(String stateRegister) {
    this.stateRegister = stateRegister;
  }

  public String getMunicipalRegister() {
    return municipalRegister;
  }

  public void setMunicipalRegister(String municipalRegister) {
    this.municipalRegister = municipalRegister;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Company)) return false;
    Company company = (Company) o;
    return getCompanyId().equals(company.getCompanyId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCompanyId());
  }
}
