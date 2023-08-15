package com.tr.domain.CostCenter;

import java.util.List;
import com.tr.domain.Titles.TitlesEntity;
import com.tr.domain.Titles.TitlesResponse;

public class CostCenterRequestDTO {

  private Long userId;
  private String name;
  private String description;
  private String observation;
  private Integer code;
  private List<TitlesResponse> titlesEntityList;

  public Long getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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
