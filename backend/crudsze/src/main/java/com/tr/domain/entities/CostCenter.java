package com.tr.domain.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cost_center")
public class CostCenter {
  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "cost_center_id")
  private Long costCenterId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "observation", columnDefinition = "Text")
  private String observation;

  @Column(name = "code")
  private Integer code;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private User user;

  public CostCenter() {}

  public CostCenter(
      Long costCenterId, String name, String description, Integer code, String observation) {
    this.costCenterId = costCenterId;
    this.name = name;
    this.description = description;
    this.code = code;
    this.observation = observation;
  }

  public CostCenter(String name, String description, Integer code, String observation) {
    this.name = name;
    this.description = description;
    this.code = code;
    this.observation = observation;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
