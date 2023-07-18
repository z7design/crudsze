package com.tr.domain.entities;

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
}
