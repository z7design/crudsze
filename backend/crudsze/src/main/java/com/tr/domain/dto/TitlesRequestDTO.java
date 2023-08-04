package com.tr.domain.dto;

import com.tr.domain.enums.TypeTitle;
import java.util.Date;
import java.util.List;

public class TitlesRequestDTO {
  private Long titlesId;
  private String name;
  private String description;
  private TypeTitle typeTitle;
  private Double valueTitle;
  private Date dateRegistration;
  private Date dateReference;
  private Date dueDate;
  private Date datePayment;
  private String observation;
  private List<CostCenterRequestDTO> costCenters;

  public Long getTitlesId() {
    return titlesId;
  }

  public void setTitlesId(Long titlesId) {
    this.titlesId = titlesId;
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

  public TypeTitle getTypeTitle() {
    return typeTitle;
  }

  public void setTypeTitle(TypeTitle typeTitle) {
    this.typeTitle = typeTitle;
  }

  public Double getValueTitle() {
    return valueTitle;
  }

  public void setValueTitle(Double valueTitle) {
    this.valueTitle = valueTitle;
  }

  public Date getDateRegistration() {
    return dateRegistration;
  }

  public void setDateRegistration(Date dateRegistration) {
    this.dateRegistration = dateRegistration;
  }

  public Date getDateReference() {
    return dateReference;
  }

  public void setDateReference(Date dateReference) {
    this.dateReference = dateReference;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Date getDatePayment() {
    return datePayment;
  }

  public void setDatePayment(Date datePayment) {
    this.datePayment = datePayment;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public List<CostCenterRequestDTO> getCostCenters() {
    return costCenters;
  }

  public void setCostCenters(List<CostCenterRequestDTO> costCenters) {
    this.costCenters = costCenters;
  }
}
