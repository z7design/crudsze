package com.tr.domain.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "patrimony")
public class Patrimony {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "patrimony_id")
  private Long patrimonyId;

  @Column(name = "code")
  private Integer code;

  @Column(name = "description")
  private String description;

  @Column(name = "location")
  private String location;

  @Column(name = "purchase_invoice")
  private String purchaseInvoice;

  @Column(name = "date_of_purchase")
  private Date dateOfPurchase;

  @Column(name = "accounting_classification")
  private String accountingClassification;

  @ManyToOne
  @JoinColumn(nullable = false)
  private LedgerAccount ledgerAccount;

  public Patrimony() {}

  public Patrimony(
      Long patrimonyId,
      Integer code,
      String description,
      String purchaseInvoice,
      Date dateOfPurchase,
      LedgerAccount ledgerAccount,
      String accountingClassification) {

    this.patrimonyId = patrimonyId;
    this.code = code;
    this.description = description;
    this.purchaseInvoice = purchaseInvoice;
    this.dateOfPurchase = dateOfPurchase;
    this.ledgerAccount = ledgerAccount;
    this.accountingClassification = accountingClassification;
  }

  public Patrimony(
      Integer code,
      String description,
      String purchaseInvoice,
      Date dateOfPurchase,
      LedgerAccount ledgerAccount,
      String accountingClassification) {

    this.code = code;
    this.description = description;
    this.purchaseInvoice = purchaseInvoice;
    this.dateOfPurchase = dateOfPurchase;
    this.ledgerAccount = ledgerAccount;
    this.accountingClassification = accountingClassification;
  }

  public Long getPatrimonyId() {
    return patrimonyId;
  }

  public void setPatrimonyId(Long patrimonyId) {
    this.patrimonyId = patrimonyId;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPurchaseInvoice() {
    return purchaseInvoice;
  }

  public void setPurchaseInvoice(String purchaseInvoice) {
    this.purchaseInvoice = purchaseInvoice;
  }

  public Date getDateOfPurchase() {
    return dateOfPurchase;
  }

  public void setDateOfPurchase(Date dateOfPurchase) {
    this.dateOfPurchase = dateOfPurchase;
  }

  public String getAccountingClassification() {
    return accountingClassification;
  }

  public void setAccountingClassification(String accountingClassification) {
    this.accountingClassification = accountingClassification;
  }

  public LedgerAccount getLedgerAccount() {
    return ledgerAccount;
  }

  public void setLedgerAccount(LedgerAccount ledgerAccount) {
    this.ledgerAccount = ledgerAccount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Patrimony))
      return false;
    Patrimony patrimony = (Patrimony) o;
    return getPatrimonyId().equals(patrimony.getPatrimonyId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPatrimonyId());
  }
}
