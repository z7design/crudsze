package com.tr.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@Table(name = "installment")
public class Installment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "installment_id")
  private Long installmentId;

  @Column(name = "installment_number")
  private Integer installmentNumber;

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "date_paiment")
  private LocalDate datePayment;

  @Column(name = "value_pay")
  private BigDecimal valuePay;

  @Column(name = "discont")
  private BigDecimal discont;

  @Column(name = "addtions")
  private BigDecimal addtions;

  @Column(name = "fines")
  private BigDecimal fines;

  public Installment() {}

  public Installment(
      Long installmentId,
      Integer installmentNumber,
      LocalDate datePayment,
      BigDecimal valuePay,
      BigDecimal discont,
      BigDecimal addtions,
      BigDecimal fines) {
    this.installmentId = installmentId;
    this.installmentNumber = installmentNumber;
    this.datePayment = datePayment;
    this.valuePay = valuePay;
    this.discont = discont;
    this.addtions = addtions;
    this.fines = fines;
  }

  public Installment(
      LocalDate datePayment,
      Integer installmentNumber,
      BigDecimal valuePay,
      BigDecimal discont,
      BigDecimal addtions,
      BigDecimal fines) {
    this.datePayment = datePayment;
    this.installmentNumber = installmentNumber;
    this.valuePay = valuePay;
    this.discont = discont;
    this.addtions = addtions;
    this.fines = fines;
  }

  public Long getInstallmentId() {
    return installmentId;
  }

  public void setInstallmentId(Long installmentId) {
    this.installmentId = installmentId;
  }

  public LocalDate getDatePayment() {
    return datePayment;
  }

  public void setDatePayment(LocalDate datePayment) {
    this.datePayment = datePayment;
  }

  public BigDecimal getValuePay() {
    return valuePay;
  }

  public void setValuePay(BigDecimal valuePay) {
    this.valuePay = valuePay;
  }

  public BigDecimal getDiscont() {
    return discont;
  }

  public void setDiscont(BigDecimal discont) {
    this.discont = discont;
  }

  public BigDecimal getAddtions() {
    return addtions;
  }

  public void setAddtions(BigDecimal additions) {
    this.addtions = additions;
  }

  public BigDecimal getFines() {
    return fines;
  }

  public void setFines(BigDecimal fines) {
    this.fines = fines;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Installment)) return false;
    Installment that = (Installment) o;
    return getInstallmentId().equals(that.getInstallmentId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getInstallmentId());
  }
}
