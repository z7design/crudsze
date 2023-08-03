package com.tr.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
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

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "observation", columnDefinition = "Text")
  private String observation;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "code")
  private Integer code;

  @ManyToMany(mappedBy = "cost_center")
  @JsonBackReference // apenas para retorno, traz apenas os centros de custos
  private List<Titles> titles;

  public CostCenter() {}

  public CostCenter(
      Long costCenterId,
      String name,
      String description,
      Integer code,
      String observation,
      List titles) {
    this.costCenterId = costCenterId;
    this.name = name;
    this.description = description;
    this.code = code;
    this.observation = observation;
    this.titles = titles;
  }

  public CostCenter(
      String name, String description, Integer code, String observation, List titles) {
    this.name = name;
    this.description = description;
    this.code = code;
    this.observation = observation;
    this.titles = titles;
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

  public List<Titles> getTitlesList() {
    return titles;
  }

  public void setTitles(List<Titles> titles) {
    this.titles = titles;
  }
}
