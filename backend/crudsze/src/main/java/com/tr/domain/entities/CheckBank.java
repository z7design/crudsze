package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "check_bank")
public class CheckBank {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "check_id")
  private Long checkId;

  @Column(name = "number")
  private Integer number;

  @Column(name = "nominal_to")
  private String nominalTo;

  @Column(name = "value")
  private BigDecimal value;

  @Column(name = "status")
  private String status;

  @Column(name = "date_emission")
  private Date dateEmission;

  @Column(name = "date_compensation")
  private Date dateCompensation;

  @Column(name = "bomPara")
  private String bomPara;

  @Column(name = "date_status")
  private Date dateStatus;

  public CheckBank() {}

  public CheckBank(
      Long checkId,
      Integer number,
      String nominalTo,
      BigDecimal value,
      String status,
      Date dateStatus,
      String bomPara,
      Date dateCompensation,
      Date dateEmission) {
    this.checkId = checkId;
    this.number = number;
    this.nominalTo = nominalTo;
    this.status = status;
    this.value = value;
    this.dateEmission = dateEmission;
    this.bomPara = bomPara;
    this.dateStatus = dateStatus;
    this.dateCompensation = dateCompensation;
  }

  public CheckBank(
      Integer number,
      String nominalTo,
      BigDecimal value,
      String status,
      Date dateStatus,
      String bomPara,
      Date dateCompensation,
      Date dateEmission) {
    this.number = number;
    this.nominalTo = nominalTo;
    this.status = status;
    this.value = value;
    this.dateEmission = dateEmission;
    this.bomPara = bomPara;
    this.dateStatus = dateStatus;
    this.dateCompensation = dateCompensation;
  }

  public Long getCheckId() {
    return checkId;
  }

  public void setCheckId(Long checkId) {
    this.checkId = checkId;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getNominalTo() {
    return nominalTo;
  }

  public void setNominalTo(String nominalTo) {
    this.nominalTo = nominalTo;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getDateEmission() {
    return dateEmission;
  }

  public void setDateEmission(Date dateEmission) {
    this.dateEmission = dateEmission;
  }

  public Date getDateCompensation() {
    return dateCompensation;
  }

  public void setDateCompensation(Date dateCompensation) {
    this.dateCompensation = dateCompensation;
  }

  public String getBomPara() {
    return bomPara;
  }

  public void setBomPara(String bomPara) {
    this.bomPara = bomPara;
  }

  public Date getDateStatus() {
    return dateStatus;
  }

  public void setDateStatus(Date dateStatus) {
    this.dateStatus = dateStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CheckBank)) return false;
    CheckBank check = (CheckBank) o;
    return getCheckId().equals(check.getCheckId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCheckId());
  }
}
