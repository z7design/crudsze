package com.tr.domain.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "driver")
public class Driver {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "driver_id")
  private Long driverId;

  @Column(name = "name")
  private String name;

  @Column(name = "dateBirthday")
  private Date dateBirthday;

  @Column(name = "general_register")
  private String generalRegister;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "phone")
  private String phone;

  @Column(name = "celphone")
  private String celphone;

  @Column(name = "email")
  private String email;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Address address;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Document document;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Departament departament;

  public Driver() {}

  public Driver(
      Long driverId,
      String name,
      Date dateBirthday,
      String generalRegister,
      String cpf,
      String phone,
      String celphone,
      String email,
      Address address,
      Document document,
      Departament departament) {
    this.driverId = driverId;
    this.name = name;
    this.dateBirthday = dateBirthday;
    this.generalRegister = generalRegister;
    this.cpf = cpf;
    this.phone = phone;
    this.celphone = celphone;
    this.email = email;
    this.address = address;
    this.document = document;
    this.departament = departament;
  }

  public Driver(
      String name,
      Date dateBirthday,
      String generalRegister,
      String cpf,
      String phone,
      String celphone,
      String email,
      Address address,
      Document document,
      Departament departament) {
    this.name = name;
    this.dateBirthday = dateBirthday;
    this.generalRegister = generalRegister;
    this.cpf = cpf;
    this.phone = phone;
    this.celphone = celphone;
    this.email = email;
    this.address = address;
    this.document = document;
    this.departament = departament;
  }

  public Long getDriverId() {
    return driverId;
  }

  public void setDriverId(Long driverId) {
    this.driverId = driverId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateBirthday() {

    return dateBirthday;
  }

  public void setDateBirthday(Date dateBirthday) {

    this.dateBirthday = dateBirthday;
  }

  public String getGeneralRegister() {

    return generalRegister;
  }

  public void setGeneralRegister(String generalRegister) {

    this.generalRegister = generalRegister;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Document getDocument() {
    return document;
  }

  public void setDocument(Document document) {
    this.document = document;
  }

  public Departament getDepartament() {
    return departament;
  }

  public void setDepartament(Departament departament) {
    this.departament = departament;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Driver)) return false;
    Driver driver = (Driver) o;
    return getDriverId().equals(driver.getDriverId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDriverId());
  }
}
