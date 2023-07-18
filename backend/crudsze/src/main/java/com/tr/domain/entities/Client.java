package com.tr.domain.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "client")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "client_id")
  private Long clientId;

  @Column(name = "name")
  private String name;

  @Column(name = "corporate_name")
  private String corporateName;

  @Column(name = "fantasy_name")
  private String fantasyName;

  @Column(name = "email")
  private String email;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "general_register")
  private String generalRegister;

  @Column(name = "entiy_registration")
  private String entiyRegistration;

  @Column(name = "date_of_birth")
  private Date dateOfBirth;

  @Column(name = "state_registration")
  private String stateRegistration;

  @Column(name = "municipal_registration")
  private String municipalRegistration;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Address address;

  @Column(name = "observation")
  private String observation;

  @Column(name = "registrarion_date")
  private Date registrarionDate;
  
  public Client() {}

  public Client(
      Long clientId,
      String name,
      String corporateName,
      String fantasyName,
      String email,
      String cpf,
      String cnpj,
      String generalRegister,
      String entiyRegistration,
      Date dateOfBirth,
      String stateRegistration,
      String municipalRegistration,
      Address address,
      String observation,
      Date registrarionDate) {

    this.clientId = clientId;
    this.name = name;
    this.corporateName = corporateName;
    this.fantasyName = fantasyName;
    this.email = email;
    this.cpf = cpf;
    this.cnpj = cnpj;
    this.generalRegister = generalRegister;
    this.entiyRegistration = entiyRegistration;
    this.dateOfBirth = dateOfBirth;
    this.stateRegistration = stateRegistration;
    this.municipalRegistration = municipalRegistration;
    this.address = address;
    this.observation = observation;
  }

  public Client(
      String name,
      String corporateName,
      String fantasyName,
      String email,
      String cpf,
      String cnpj,
      String generalRegister,
      String entiyRegistration,
      Date dateOfBirth,
      String stateRegistration,
      String municipalRegistration,
      Address address,
      String observation,
      Date registrarionDate) {

    this.name = name;
    this.corporateName = corporateName;
    this.fantasyName = fantasyName;
    this.email = email;
    this.cpf = cpf;
    this.cnpj = cnpj;
    this.generalRegister = generalRegister;
    this.entiyRegistration = entiyRegistration;
    this.dateOfBirth = dateOfBirth;
    this.stateRegistration = stateRegistration;
    this.municipalRegistration = municipalRegistration;
    this.address = address;
    this.observation = observation;
    this.registrarionDate = registrarionDate;
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCorporateName() {
    return corporateName;
  }

  public void setCorporateName(String corporateName) {
    this.corporateName = corporateName;
  }

  public String getFantasyName() {
    return fantasyName;
  }

  public void setFantasyName(String fantasyName) {
    this.fantasyName = fantasyName;
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

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getGeneralRegister() {
    return generalRegister;
  }

  public void setGeneralRegister(String generalRegister) {
    this.generalRegister = generalRegister;
  }

  public String getEntiyRegistration() {
    return entiyRegistration;
  }

  public void setEntiyRegistration(String entiyRegistration) {
    this.entiyRegistration = entiyRegistration;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getStateRegistration() {
    return stateRegistration;
  }

  public void setStateRegistration(String stateRegistration) {
    this.stateRegistration = stateRegistration;
  }

  public String getMunicipalRegistration() {
    return municipalRegistration;
  }

  public void setMunicipalRegistration(String municipalRegistration) {
    this.municipalRegistration = municipalRegistration;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
  
  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public Date getRegistrarionDate() {
    return registrarionDate;
  }

  public void setRegistrarionDate(Date registrarionDate) {
    this.registrarionDate = registrarionDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Client)) return false;
    Client client = (Client) o;
    return getClientId().equals(client.getClientId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getClientId());
  }
}
