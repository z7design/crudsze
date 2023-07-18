package com.tr.domain.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "checkbook")
public class Checkbook {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "checkbook_id")
  private Long checkbookId;

  @Column(name = "number_talon")
  private Integer numberTalon;

  @Column(name = "status")
  private String status;

  @Column(name = "emission_date")
  private Date emissionDate;

  public Checkbook() {}

  public Checkbook(Long checkbookId, Integer numberTalon, String status, Date emissionDate) {
    this.checkbookId = checkbookId;
    this.numberTalon = numberTalon;
    this.status = status;
    this.emissionDate = emissionDate;
  }

  public Checkbook(Integer numberTalon, String status, Date emissionDate) {
    this.numberTalon = numberTalon;
    this.status = status;
    this.emissionDate = emissionDate;
  }

  public Long getCheckbookId() {
    return checkbookId;
  }

  public void setCheckbookId(Long checkbookId) {
    this.checkbookId = checkbookId;
  }

  public Integer getNumberTalon() {
    return numberTalon;
  }

  public void setNumberTalon(Integer numberTalon) {
    this.numberTalon = numberTalon;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getEmissionDate() {
    return emissionDate;
  }

  public void setEmissionDate(Date emissionDate) {
    this.emissionDate = emissionDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Checkbook)) return false;
    Checkbook checkbook = (Checkbook) o;
    return getCheckbookId().equals(checkbook.getCheckbookId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCheckbookId());
  }
}
