package com.tr.domain.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "account_bank")
public class AccountBank implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "account_bank_id")
  private Long accountBankId;

  @Column(name = "agnecy")
  private String agency;

  @Column(name = "agency_dv")
  private String agencyDV;

  @Column(name = "account")
  private String account;

  @Column(name = "account_dv")
  private String accountDV;

  public AccountBank() {}

  public AccountBank(
      Long accountBankId, String agency, String agencyDV, String account, String accountDV) {
    this.accountBankId = accountBankId;
    this.agency = agency;
    this.agencyDV = agencyDV;
    this.account = account;
    this.accountDV = accountDV;
  }

  public AccountBank(String agency, String agencyDV, String account, String accountDV) {
    this.agency = agency;
    this.agencyDV = agencyDV;
    this.account = account;
    this.accountDV = accountDV;
  }

  public Long getAccountBankId() {
    return accountBankId;
  }

  public void setAccountBankId(Long accountBankId) {
    this.accountBankId = accountBankId;
  }

  public String getAgency() {
    return agency;
  }

  public void setAgency(String agency) {
    this.agency = agency;
  }

  public String getAgencyDV() {
    return agencyDV;
  }

  public void setAgencyDV(String agencyDV) {
    this.agencyDV = agencyDV;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getAccountDV() {
    return accountDV;
  }

  public void setAccountDV(String accountDV) {
    this.accountDV = accountDV;
  }
  
  
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof AccountBank))
      return false;
    AccountBank that = (AccountBank) o;
    return getAccountBankId().equals(that.getAccountBankId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAccountBankId());
  }
}
