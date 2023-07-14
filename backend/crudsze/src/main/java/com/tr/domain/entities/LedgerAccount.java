package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ledger_account")
public class LedgerAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ledgerAccount_id")
  private Long ledgerAccountId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "code")
  private Integer code;

  @ManyToOne
  @JoinColumn(nullable = false)
  private CostCenter costCenter;

  public LedgerAccount() {}

  public LedgerAccount(Long ledgerAccountId, String name, String description, Integer code) {
    this.ledgerAccountId = ledgerAccountId;
    this.description = description;
    this.name = name;
    this.code = code;
  }

  public LedgerAccount(String name, String description, Integer code) {
    this.name = name;
    this.description = description;
    this.code = code;
  }

  public Long getLedgerAccountId() {
    return ledgerAccountId;
  }

  public void setLedgerAccountId(Long ledgerAccountId) {
    this.ledgerAccountId = ledgerAccountId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public CostCenter getCostCenter() {
    return costCenter;
  }

  public void setCostCenter(CostCenter costCenter) {
    this.costCenter = costCenter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof LedgerAccount))
      return false;
    LedgerAccount that = (LedgerAccount) o;
    return getLedgerAccountId().equals(that.getLedgerAccountId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLedgerAccountId());
  }
}
