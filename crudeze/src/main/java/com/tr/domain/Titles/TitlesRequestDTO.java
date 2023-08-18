package com.tr.domain.Titles;

import com.tr.domain.CostCenter.CostCenterEntity;
import com.tr.domain.CostCenter.CostCenterRequestDTO;
import com.tr.domain.Enums.TypeTitle;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.*;

public class TitlesRequestDTO {

  private Long titleId;
  private String name;
  private TypeTitle typeTitle;
  private String description;
  private BigDecimal valueTitle;
  private Date dateRegistration;
  private Date dateReference;
  private Date dueDate;
  private Date datePayment;
  private String observation;
  private List<CostCenterRequestDTO> costCenter;

  public List<CostCenterRequestDTO> getCostCenter() {
    return costCenter;
  }

  public void setCostCenter(List<CostCenterRequestDTO> costCenter) {
    this.costCenter = costCenter;
  }

  public Long getTitleId() {
    return titleId;
  }

  public void setTitleId(Long titleId) {
    this.titleId = titleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TypeTitle getTypeTitle() {
    return typeTitle;
  }

  public void setTypeTitle(TypeTitle typeTitle) {
    this.typeTitle = typeTitle;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getValueTitle() {
    return valueTitle;
  }

  public void setValueTitle(BigDecimal valueTitle) {
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

}
