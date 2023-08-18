package com.tr.domain.CostCenter;

import java.util.List;
import com.tr.domain.Titles.TitlesResponse;

public class CostCenterRequestDTO {

  private Long costCenterId;
  private Long userId;
  private String name;
  private String description;
  private String observation;
  private Integer code;

  public CostCenterRequestDTO() {
  }

  public Long getCostCenterId() {
    return costCenterId;
  }

  public void setCostCenterId(Long costCenterId) {
    this.costCenterId = costCenterId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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
