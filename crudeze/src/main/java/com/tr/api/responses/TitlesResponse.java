package com.tr.api.responses;

import java.util.Date;
import java.util.List;
import com.tr.domain.Enums.TypeTitle;

public class TitlesResponse {
  private Long titlesId;
  private String name;
  private String description;
  private Double valueTitle;
  private Date dateRegistration;
  private Date dateReference;
  private Date dueDate;
  private Date datePayment;
  private String observation;
  private TypeTitle typeTitle;
  private List<CostCenterResponse> centerResponseList;

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

  public TypeTitle getTypeTitle() {
    return typeTitle;
  }

  public void setTypeTitle(TypeTitle typeTitle) {
    this.typeTitle = typeTitle;
  }
}
