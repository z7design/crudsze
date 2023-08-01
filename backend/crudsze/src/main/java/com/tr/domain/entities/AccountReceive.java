package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "account_receive")
public class AccountReceive {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "account_receive_id")
  private Long accountReceiveId;

  @Column(name = "number_document")
  private Integer numberDocument;

  @Column(name = "date_document")
  private Date dateDocument;

  @Column(name = "due_date")
  private Date dueDate;

  @Column(name = "pay_date")
  private Date payDate;

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

  @Column(name = "value_account_receivable")
  private BigDecimal valueAccountReceivable;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Client client;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Category category;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Installment installment;

  public AccountReceive() {}

  public AccountReceive(
      Long accountReceiveId,
      Integer numberDocument,
      Date dateDocument,
      Date dueDate,
      Date payDate,
      String description,
      BigDecimal amountPaid,
      BigDecimal diference,
      String status,
      BigDecimal valueOfDocument,
      BigDecimal valueAccountReceivable,
      Client client,
      Category category,
      Installment installment) {
    this.accountReceiveId = accountReceiveId;
    this.numberDocument = numberDocument;
    this.dateDocument = dateDocument;
    this.dueDate = dueDate;
    this.payDate = payDate;
    this.description = description;
    this.amountPaid = amountPaid;
    this.diference = diference;
    this.status = status;
    this.valueOfDocument = valueOfDocument;
    this.valueAccountReceivable = valueAccountReceivable;
    this.client = client;
    this.category = category;
    this.installment = installment;
  }

  public AccountReceive(
      Integer numberDocument,
      Date dateDocument,
      Date dueDate,
      Date payDate,
      String description,
      BigDecimal amountPaid,
      BigDecimal diference,
      String status,
      BigDecimal valueOfDocument,
      BigDecimal valueAccountReceivable,
      Client client,
      Category category,
      Installment installment) {
    this.numberDocument = numberDocument;
    this.dateDocument = dateDocument;
    this.dueDate = dueDate;
    this.payDate = payDate;
    this.description = description;
    this.amountPaid = amountPaid;
    this.diference = diference;
    this.status = status;
    this.valueOfDocument = valueOfDocument;
    this.valueAccountReceivable = valueAccountReceivable;
    this.client = client;
    this.category = category;
    this.installment = installment;
  }

  public Long getAccountReceiveId() {
    return accountReceiveId;
  }

  public void setAccountReceiveId(Long accountReceiveId) {
    this.accountReceiveId = accountReceiveId;
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

  public Date getPayDate() {
    return payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
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

  public BigDecimal getValueAccountReceivable() {
    return valueAccountReceivable;
  }

  public void setValueAccountReceivable(BigDecimal valueAccountReceivable) {
    this.valueAccountReceivable = valueAccountReceivable;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
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
    if (!(o instanceof AccountReceive)) return false;
    AccountReceive that = (AccountReceive) o;
    return getAccountReceiveId().equals(that.getAccountReceiveId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAccountReceiveId());
  }
}
