package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bank")
public class Bank {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "bank_id")
  private Long banckId;

  @Column(name = "name")
  private String name;

  @Column(name = "agency")
  private String agency;

  @Column(name = "code")
  private Integer code;

  @Column(name = "cheking_account")
  private String chekingAccount;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Address address;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AccountBank accountBank;

  public Bank() {}

  public Bank(
      Long banckId,
      String name,
      String agency,
      Integer code,
      String chekingAccount,
      Address address,
      AccountBank accountBank) {
    this.banckId = banckId;
    this.name = name;
    this.agency = agency;
    this.code = code;
    this.chekingAccount = chekingAccount;
    this.address = address;
    this.accountBank = accountBank;
  }

  public Bank(
      String name,
      String agency,
      Integer code,
      String chekingAccount,
      Address address,
      AccountBank accountBank) {
    this.name = name;
    this.agency = agency;
    this.code = code;
    this.chekingAccount = chekingAccount;
    this.address = address;
    this.accountBank = accountBank;
  }

  public Long getBanckId() {
    return banckId;
  }

  public void setBanckId(Long banckId) {
    this.banckId = banckId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAgency() {
    return agency;
  }

  public void setAgency(String agency) {
    this.agency = agency;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getChekingAccount() {
    return chekingAccount;
  }

  public void setChekingAccount(String chekingAccount) {
    this.chekingAccount = chekingAccount;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public AccountBank getAccountBank() {
    return accountBank;
  }

  public void setAccountBank(AccountBank accountBank) {
    this.accountBank = accountBank;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Bank)) return false;
    Bank bank = (Bank) o;
    return getBanckId().equals(bank.getBanckId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBanckId());
  }
}
