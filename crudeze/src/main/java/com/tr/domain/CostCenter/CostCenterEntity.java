package com.tr.domain.CostCenter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tr.domain.Titles.TitlesEntity;
import com.tr.domain.User.UserEntity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "costcenter")
public class CostCenterEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "costcenter_id")
  private Long costCenterId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "observation", columnDefinition = "Text")
  private String observation;

  @Column(name = "code", nullable = false, unique = true)
  private Integer code;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  @ManyToMany(mappedBy = "costCenter")
  @JsonBackReference
  private List<TitlesEntity> titlesEntity;

  public CostCenterEntity() {
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

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  public List<TitlesEntity> getTitlesEntity() {
    return titlesEntity;
  }

  public void setTitlesEntity(List<TitlesEntity> titlesEntity) {
    this.titlesEntity = titlesEntity;
  }
}
