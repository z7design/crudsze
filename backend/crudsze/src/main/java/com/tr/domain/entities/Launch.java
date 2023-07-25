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

  @Column(name = "value")
  private BigDecimal value;

  @Column(name = "tax")
  private BigDecimal tax;

  @Column(name = "type_launch")
  @Enumerated(EnumType.STRING)
  // @Convert(converter = TypeLaunch.class)
  private TypeLaunch typeLaunch;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  // @Convert(converter = StatusLaunch.class)
  private StastusLaunch status;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AccountPayble accountPayble;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AccountReceive accountReceive;

  public Launch() {}

  public Launch(
      Long launchId,
      TypeLaunch typeLaunch,
      BigDecimal value,
      BigDecimal tax,
      AccountPayble accountPayble,
      AccountReceive accountReceive) {
    this.launchId = launchId;
    this.typeLaunch = typeLaunch;
    this.value = value;
    this.tax = tax;
    this.accountPayble = accountPayble;
    this.accountReceive = accountReceive;
  }

  public Launch(
      TypeLaunch typeLaunch,
      BigDecimal value,
      BigDecimal tax,
      AccountPayble accountPayble,
      AccountReceive accountReceive) {
    this.typeLaunch = typeLaunch;
    this.value = value;
    this.tax = tax;
    this.accountPayble = accountPayble;
    this.accountReceive = accountReceive;
  }

  public Long getLaunchId() {
    return launchId;
  }

  public void setLaunchId(Long launchId) {
    this.launchId = launchId;
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

  public TypeLaunch getTypeLaunch() {
    return typeLaunch;
  }

  public void setTypeLaunch(TypeLaunch typeLaunch) {
    this.typeLaunch = typeLaunch;
  }

  public StastusLaunch getStatus() {
    return status;
  }

  public void setStatus(StastusLaunch status) {
    this.status = status;
  }

  public AccountPayble getAccountPayble() {
    return accountPayble;
  }

  public void setAccountPayble(AccountPayble accountPayble) {
    this.accountPayble = accountPayble;
  }

  public AccountReceive getAccountReceive() {
    return accountReceive;
  }

  public void setAccountReceive(AccountReceive accountReceive) {
    this.accountReceive = accountReceive;
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
