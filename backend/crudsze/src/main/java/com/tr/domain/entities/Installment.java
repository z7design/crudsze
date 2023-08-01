package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "installment")
public class Installment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "installment_id")
  private Long installmentId;

  @Column(name = "date_paiment")
  private Date datePayment;

  @Column(name = "value_pay")
  private BigDecimal valuePay;

  @Column(name = "discont")
  private BigDecimal discont;

  @Column(name = "additions")
  private BigDecimal additions;

  @Column(name = "fines")
  private BigDecimal fines;

  @ManyToOne
  @JoinColumn(nullable = true)
  private Launch launch;

  public Installment() {}

  public Installment(
      Long installmentId,
      Date datePayment,
      BigDecimal valuePay,
      BigDecimal discont,
      BigDecimal additions,
      BigDecimal fines,
      Launch launch) {
    this.installmentId = installmentId;
    this.datePayment = datePayment;
    this.valuePay = valuePay;
    this.discont = discont;
    this.additions = additions;
    this.fines = fines;
  }

  public Installment(
      Date datePayment,
      BigDecimal valuePay,
      BigDecimal discont,
      BigDecimal additions,
      BigDecimal fines,
      Launch launch) {
    this.datePayment = datePayment;
    this.valuePay = valuePay;
    this.discont = discont;
    this.additions = additions;
    this.fines = fines;
  }

  public Long getInstallmentId() {
    return installmentId;
  }

  public void setInstallmentId(Long installmentId) {
    this.installmentId = installmentId;
  }

  public Date getDatePayment() {
    return datePayment;
  }

  public void setDatePayment(Date datePayment) {
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

  public BigDecimal getAdditions() {
    return additions;
  }

  public void setAdditions(BigDecimal additions) {
    this.additions = additions;
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
