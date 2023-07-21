package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employees {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employess_id")
  private Long employeesId;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "genereal_register")
  private String generealRegister;

  @Column(name = "salary")
  private BigDecimal salary;

  @Column(name = "function")
  private String function;

  @Column(name = "number_licence_driver")
  private String numberLicenceDriver;

  @Column(name = "spouse")
  private String spouse;

  @Column(name = "number_children")
  private Integer numberChildren;

  @Column(name = "schooling")
  private String schooling;

  @Column(name = "work_hours")
  private String workHours;

  @Column(name = "foto")
  private String foto;

  @Column(name = "birthday")
  private Date birthday;

  @Column(name = "work_card")
  private String workCard;

  @Column(name = "pispasep")
  private String pispasep;

  @Column(name = "admission")
  private Date admission;

  @Column(name = "demission")
  private Date demission;

  @Column(name = "exames")
  private String exames;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Address address;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Departament departament;

  public Employees() {}

  public Employees(
      Long employeesId,
      String name,
      String email,
      String cpf,
      String generealRegister,
      BigDecimal salary,
      String function,
      String numberLicenceDriver,
      String spouse,
      Integer numberChildren,
      String schooling,
      String workHours,
      String foto,
      Date birthday,
      String workCard,
      String pispasep,
      Date admission,
      Date demission,
      String exames,
      Address address,
      Departament departament) {
    this.employeesId = employeesId;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.generealRegister = generealRegister;
    this.salary = salary;
    this.function = function;
    this.numberLicenceDriver = numberLicenceDriver;
    this.spouse = spouse;
    this.numberChildren = numberChildren;
    this.schooling = schooling;
    this.workHours = workHours;
    this.foto = foto;
    this.birthday = birthday;
    this.workCard = workCard;
    this.pispasep = pispasep;
    this.admission = admission;
    this.demission = demission;
    this.exames = exames;
    this.address = address;
    this.departament = departament;
  }

  public Employees(
      String name,
      String email,
      String cpf,
      String generealRegister,
      BigDecimal salary,
      String function,
      String numberLicenceDriver,
      String spouse,
      Integer numberChildren,
      String schooling,
      String workHours,
      String foto,
      Date birthday,
      String workCard,
      String pispasep,
      Date admission,
      Date demission,
      String exames,
      Address address,
      Departament departament) {
    this.employeesId = employeesId;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.generealRegister = generealRegister;
    this.departament = departament;
    this.salary = salary;
    this.function = function;
    this.numberLicenceDriver = numberLicenceDriver;
    this.spouse = spouse;
    this.numberChildren = numberChildren;
    this.schooling = schooling;
    this.workHours = workHours;
    this.foto = foto;
    this.birthday = birthday;
    this.workCard = workCard;
    this.pispasep = pispasep;
    this.admission = admission;
    this.demission = demission;
    this.exames = exames;
    this.address = address;
  }

  public Long getEmployeesId() {
    return employeesId;
  }

  public void setEmployeesId(Long employeesId) {
    this.employeesId = employeesId;
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

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getGenerealRegister() {
    return generealRegister;
  }

  public void setGenerealRegister(String generealRegister) {
    this.generealRegister = generealRegister;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public String getFunction() {
    return function;
  }

  public void setFunction(String function) {
    this.function = function;
  }

  public String getNumberLicenceDriver() {
    return numberLicenceDriver;
  }

  public void setNumberLicenceDriver(String numberLicenceDriver) {
    this.numberLicenceDriver = numberLicenceDriver;
  }

  public String getSpouse() {
    return spouse;
  }

  public void setSpouse(String spouse) {
    this.spouse = spouse;
  }

  public Integer getNumberChildren() {
    return numberChildren;
  }

  public void setNumberChildren(Integer numberChildren) {
    this.numberChildren = numberChildren;
  }

  public String getSchooling() {
    return schooling;
  }

  public void setSchooling(String schooling) {
    this.schooling = schooling;
  }

  public String getWorkHours() {
    return workHours;
  }

  public void setWorkHours(String workHours) {
    this.workHours = workHours;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getWorkCard() {
    return workCard;
  }

  public void setWorkCard(String workCard) {
    this.workCard = workCard;
  }

  public String getPispasep() {
    return pispasep;
  }

  public void setPispasep(String pispasep) {
    this.pispasep = pispasep;
  }

  public Date getAdmission() {
    return admission;
  }

  public void setAdmission(Date admission) {
    this.admission = admission;
  }

  public Date getDemission() {
    return demission;
  }

  public void setDemission(Date demission) {
    this.demission = demission;
  }

  public String getExames() {
    return exames;
  }

  public void setExames(String exames) {
    this.exames = exames;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Departament getDepartament() {
    return departament;
  }

  public void setDepartament(Departament departament) {
    this.departament = departament;
  }
}
