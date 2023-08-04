package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "account_payble")
public class AccountPayble {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EmbeddedId
  @Column(name = "account_payble_id")
  private Long accountPaybleId;

  @Column(name = "number_document")
  private Integer numberDocument;

  @Column(name = "date_document")
  private Date dateDocument;

  @Column(name = "due_date")
  private Date dueDate;

  @Column(name = "description")
  private String description;

  @Column(name = "amount_paid")
  private BigDecimal amountPaid;

  @Column(name = "diference")
  private BigDecimal diference;

  @Column(name = "status")
  private String status;

  @Column(name = "value_of_Document")
  private BigDecimal valueOfDocument;

  @Column(name = "value_account_pay")
  private BigDecimal valueAccountPay;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Supplier supplier;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Installment installment;
  

  public AccountPayble() {}

  public AccountPayble(
      Long accountPaybleId,
      Integer numberDocument,
      Date dateDocument,
      Date dueDate,
      String description,
      String status,
      BigDecimal amountPaid,
      BigDecimal diference,
      BigDecimal valueOfDocument,
      BigDecimal valueAccountPay,
      Supplier supplier,
      Installment installment) {
    this.accountPaybleId = accountPaybleId;
    this.numberDocument = numberDocument;
    this.dateDocument = dateDocument;
    this.dueDate = dueDate;
    this.description = description;
    this.amountPaid = amountPaid;
    this.diference = diference;
    this.status = status;
    this.valueOfDocument = valueOfDocument;
    this.valueAccountPay = valueAccountPay;
    this.supplier = supplier;
    this.installment = installment;
  }

  public AccountPayble(
      Integer numberDocument,
      Date dateDocument,
      Date dueDate,
      String description,
      BigDecimal amountPaid,
      BigDecimal diference,
      String status,
      BigDecimal valueOfDocument,
      BigDecimal valueAccountPay,
      Supplier supplier,
      Installment installment) {
    this.numberDocument = numberDocument;
    this.dateDocument = dateDocument;
    this.dueDate = dueDate;
    this.description = description;
    this.amountPaid = amountPaid;
    this.diference = diference;
    this.status = status;
    this.valueOfDocument = valueOfDocument;
    this.valueAccountPay = valueAccountPay;
    this.supplier = supplier;
    this.installment = installment;
  }

  public Long getAccountPaybleId() {
    return accountPaybleId;
  }

  public void setAccountPaybleId(Long accountPaybleId) {
    this.accountPaybleId = accountPaybleId;
  }

  public Integer getNumberDocument() {
    return numberDocument;
  }

  public void setNumberDocument(Integer numberDocument) {
    this.numberDocument = numberDocument;
  }

  public Date getDateDocument() {
    return dateDocument;
  }

  public void setDateDocument(Date dateDocument) {
    this.dateDocument = dateDocument;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getAmountPaid() {
    return amountPaid;
  }

  public void setAmountPaid(BigDecimal amountPaid) {
    this.amountPaid = amountPaid;
  }

  public BigDecimal getDiference() {
    return diference;
  }

  public void setDiference(BigDecimal diference) {
    this.diference = diference;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BigDecimal getValueOfDocument() {
    return valueOfDocument;
  }

  public void setValueOfDocument(BigDecimal valueOfDocument) {
    this.valueOfDocument = valueOfDocument;
  }

  public BigDecimal getValueAccountPay() {
    return valueAccountPay;
  }

  public void setValueAccountPay(BigDecimal valueAccountPay) {
    this.valueAccountPay = valueAccountPay;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public Installment getInstallment() {
    return installment;
  }

  public void setInstallment(Installment installment) {
    this.installment = installment;
  }
  

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountPayble)) return false;
    AccountPayble that = (AccountPayble) o;
    return getAccountPaybleId().equals(that.getAccountPaybleId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAccountPaybleId());
  }
}
