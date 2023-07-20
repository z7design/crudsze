package com.tr.domain.entities;

import com.tr.domain.enums.StastusLaunch;
import com.tr.domain.enums.TypeLaunch;
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
  @Enumerated(EnumType.STRING)
  // @Convert(converter = TypeLaunch.class)
  private TypeLaunch typeLaunch;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  // @Convert(converter = StatusLaunch.class)
  private StastusLaunch status;

  @Column(name = "value")
  private BigDecimal value;

  @Column(name = "tax")
  private BigDecimal tax;

  public Launch() {}

  public Launch(Long launchId, TypeLaunch typeLaunch, BigDecimal value, BigDecimal tax) {
    this.launchId = launchId;
    this.typeLaunch = typeLaunch;
    this.value = value;
    this.tax = tax;
  }

  public Launch(TypeLaunch typeLaunch, BigDecimal value, BigDecimal tax) {
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

  public TypeLaunch getTypeLaunch() {
    return typeLaunch;
  }

  public void setTypeLaunch(TypeLaunch typeLaunch) {
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
