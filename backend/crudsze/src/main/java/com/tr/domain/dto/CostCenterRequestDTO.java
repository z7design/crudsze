package com.tr.domain.dto;

import com.tr.domain.entities.CostCenter;

public class CostCenterRequestDTO {
  private Long costCenterId;
  private String name;
  private String description;
  private Integer code;
  private String observation;

  public CostCenterRequestDTO() {}

  public CostCenterRequestDTO(
      Long costCenterId, String name, String description, String observation, Integer code) {
    this.costCenterId = costCenterId;
    this.name = name;
    this.code = code;
    this.description = description;
    this.observation = observation;
  }

  public CostCenterRequestDTO(CostCenter costCenter) {
    this.costCenterId = costCenter.getCostCenterId();
    this.name = costCenter.getName();
    this.code = costCenter.getCode();
    this.description = costCenter.getDescription();
    this.observation = costCenter.getObservation();
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

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
