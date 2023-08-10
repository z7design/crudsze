package com.tr.api.responses;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class CashFlowResponse {

  private Long cashFlowId;

  @Column(name = "dute_date")
  private Date duteDate;

  @Column(name = "payment_date")
  private Date paymentDate;

  @Column(name = "month_reference")
  private Date monthReference;

  @Column(name = "description")
  private String description;

  @Column(name = "payment_amount")
  private BigDecimal paymentAmount;

  @Column(name = "intallment_number")
  private String intallmentNumber;

  @Column(name = "total_payable")
  private BigDecimal totalPayable;

  @Column(name = "total_receivable")
  private BigDecimal totalReceivable;

  @Column(name = "balance")
  private BigDecimal balance;

  public CashFlowResponse() {}

  public CashFlowResponse(
      Long cashFlowId,
      Date duteDate,
      Date paymentDate,
      Date monthReference,
      String description,
      BigDecimal paymentAmount,
      String intallmentNumber,
      BigDecimal totalPayable,
      BigDecimal totalReceivable,
      BigDecimal balance) {
    this.cashFlowId = cashFlowId;
    this.duteDate = duteDate;
    this.paymentDate = paymentDate;
    this.monthReference = monthReference;
    this.description = description;
    this.paymentAmount = paymentAmount;
    this.intallmentNumber = intallmentNumber;
    this.totalPayable = totalPayable;
    this.totalReceivable = totalReceivable;
    this.balance = balance;
  }

  public Long getCashFlowId() {
    return cashFlowId;
  }

  public void setCashFlowId(Long cashFlowId) {
    this.cashFlowId = cashFlowId;
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

  public Date getMonthReference() {
    return monthReference;
  }

  public void setMonthReference(Date monthReference) {
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

  public String getIntallmentNumber() {
    return intallmentNumber;
  }

  public void setIntallmentNumber(String intallmentNumber) {
    this.intallmentNumber = intallmentNumber;
  }

  public BigDecimal getTotalPayable() {
    return totalPayable;
  }

  public void setTotalPayable(BigDecimal totalPayable) {
    this.totalPayable = totalPayable;
  }

  public BigDecimal getTotalReceivable() {
    return totalReceivable;
  }

  public void setTotalReceivable(BigDecimal totalReceivable) {
    this.totalReceivable = totalReceivable;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
  
}
