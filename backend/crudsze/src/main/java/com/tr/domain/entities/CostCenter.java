package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cost_center")
public class CostCenter {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "cost_centerId_id")
  private Long costCenterId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "code")
  private Integer code;

  public CostCenter() {}

  public CostCenter(Long costCenterId, String name, String description, Integer code) {
    this.costCenterId = costCenterId;
    this.name = name;
    this.description = description;
    this.code = code;
  }

  public CostCenter(String name, String description, Integer code) {
    this.name = name;
    this.description = description;
    this.code = code;
  }

  public Long getCostCenterId() {
    return costCenterId;
  }

  public void setCostCenterId(Long costCenterId) {
    this.costCenterId = costCenterId;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CostCenter)) return false;
    CostCenter that = (CostCenter) o;
    return getCostCenterId().equals(that.getCostCenterId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCostCenterId());
  }
}
