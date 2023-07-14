package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "check")
public class Check {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @ManyToOne
  @JoinColumn(nullable = false)
  private Checkbook checkbook;

  public Check() {}

  public Check(
      Long checkId,
      Integer number,
      String nominalTo,
      BigDecimal value,
      String status,
      Date dateStatus,
      String bomPara,
      Date dateCompensation,
      Date dateEmission,
      Checkbook checkbook) {
    this.checkId = checkId;
    this.nominalTo = nominalTo;
    this.status = status;
    this.value = value;
    this.dateEmission = dateEmission;
    this.bomPara = bomPara;
    this.dateStatus = dateStatus;
    this.dateCompensation = dateCompensation;
    this.checkbook = checkbook;
  }

  public Check(
      Integer number,
      String nominalTo,
      BigDecimal value,
      String status,
      Date dateStatus,
      String bomPara,
      Date dateCompensation,
      Date dateEmission,
      Checkbook checkbook) {
    this.checkId = checkId;
    this.nominalTo = nominalTo;
    this.status = status;
    this.value = value;
    this.dateEmission = dateEmission;
    this.bomPara = bomPara;
    this.dateStatus = dateStatus;
    this.dateCompensation = dateCompensation;
    this.checkbook = checkbook;
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

  public Checkbook getCheckbook() {
    return checkbook;
  }

  public void setCheckbook(Checkbook checkbook) {
    this.checkbook = checkbook;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Check)) return false;
    Check check = (Check) o;
    return getCheckId().equals(check.getCheckId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCheckId());
  }
}
