package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "launch")
public class Launch {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "launch_id")
  private Long launchId;

  @Column(name = "type_launch")
  private String typeLaunch;

  @Column(name = "value")
  private BigDecimal value;

  @Column(name = "tax")
  private BigDecimal tax;

  public Launch() {}

  public Launch(Long launchId, String typeLaunch, BigDecimal value, BigDecimal tax) {
    this.launchId = launchId;
    this.typeLaunch = typeLaunch;
    this.value = value;
    this.tax = tax;
  }

  public Launch(String typeLaunch, BigDecimal value, BigDecimal tax) {
    this.typeLaunch = typeLaunch;
    this.value = value;
    this.tax = tax;
  }

  public Long getLaunchId() {
    return launchId;
  }

  public void setLaunchId(Long launchId) {
    this.launchId = launchId;
  }

  public String getTypeLaunch() {
    return typeLaunch;
  }

  public void setTypeLaunch(String typeLaunch) {
    this.typeLaunch = typeLaunch;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public BigDecimal getTax() {
    return tax;
  }

  public void setTax(BigDecimal tax) {
    this.tax = tax;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Launch)) return false;
    Launch launch = (Launch) o;
    return getLaunchId().equals(launch.getLaunchId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLaunchId());
  }
}
