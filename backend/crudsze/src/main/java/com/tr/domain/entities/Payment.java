package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "payment")
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "payment_id")
  private Long paymentId;

  @Column(name = "dute_date")
  private Date duteDate;

  @Column(name = "payment_date")
  private Date paymentDate;

  @Column(name = "month_reference")
  private String monthReference;

  @Column(name = "description")
  private String description;

  @Column(name = "payment_amount")
  private BigDecimal paymentAmount;

  @Column(name = "installment_number")
  private Integer installmentNumber;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AccountPayble accountPayble;

  public Payment() {}

  public Payment(
      Long paymentId,
      Date duteDate,
      Date paymentDate,
      String monthReference,
      String description,
      BigDecimal paymentAmount,
      Integer installmentNumber,
      AccountPayble accountPayble) {
    this.paymentId = paymentId;
    this.duteDate = duteDate;
    this.paymentDate = paymentDate;
    this.monthReference = monthReference;
    this.description = description;
    this.paymentAmount = paymentAmount;
    this.installmentNumber = installmentNumber;
    this.accountPayble = accountPayble;
  }

  public Payment(
      Date duteDate,
      Date paymentDate,
      String monthReference,
      String description,
      BigDecimal paymentAmount,
      Integer installmentNumber,
      AccountPayble accountPayble) {
    this.duteDate = duteDate;
    this.paymentDate = paymentDate;
    this.monthReference = monthReference;
    this.description = description;
    this.paymentAmount = paymentAmount;
    this.installmentNumber = installmentNumber;
    this.accountPayble = accountPayble;
  }

  public AccountPayble getAccountPayble() {
    return accountPayble;
  }

  public void setAccountPayble(AccountPayble accountPayble) {
    this.accountPayble = accountPayble;
  }

  public Long getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(Long paymentId) {
    this.paymentId = paymentId;
  }

  public Date getDuteDate() {
    return duteDate;
  }

  public void setDuteDate(Date duteDate) {
    this.duteDate = duteDate;
  }

  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }

  public String getMonthReference() {
    return monthReference;
  }

  public void setMonthReference(String monthReference) {
    this.monthReference = monthReference;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPaymentAmount() {
    return paymentAmount;
  }

  public void setPaymentAmount(BigDecimal paymentAmount) {
    this.paymentAmount = paymentAmount;
  }

  public Integer getInstallmentNumber() {
    return installmentNumber;
  }

  public void setInstallmentNumber(Integer installmentNumber) {
    this.installmentNumber = installmentNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Payment)) return false;
    Payment payment = (Payment) o;
    return getPaymentId().equals(payment.getPaymentId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPaymentId());
  }
}
