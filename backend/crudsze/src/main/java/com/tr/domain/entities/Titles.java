package com.tr.domain.entities;

import com.tr.domain.enums.TypeTitle;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "titles")
public class Titles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "titles_id")
  private Long titlesId;

  @Column(name = "name")
  private String name;

  @Column(nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private TypeTitle typeTitle;

  /*  @ManyToOne
  @JoinColumn(name = "cost_centerId_id")
  private CostCenter costCenter;*/

  @ManyToMany
  @JoinTable(
      name = "titles_costCenter",
      joinColumns = @JoinColumn(name = "titles_id"),
      inverseJoinColumns = @JoinColumn(name = "cost_centerId_id"))
  private List<CostCenter> costCenterList;

  @Column(nullable = false)
  private BigDecimal valueTitle;

  @Column(nullable = false)
  private Double valor;

  @Column(name = "date_registration")
  private Date dateRegistration;

  @Column(name = "date_reference")
  private Date dateReference;

  @Column(name = "due_date")
  private Date dueDate;

  @Column(name = "date_payment")
  private Date datePayment;

  @Column(name = "observation", columnDefinition = "TEXT")
  private String observation;

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public TypeTitle getTypeTitle() {
    return typeTitle;
  }

  public void setTypeTitle(TypeTitle typeTitle) {
    this.typeTitle = typeTitle;
  }

  public List<CostCenter> getCostCenterList() {
    return costCenterList;
  }

  public void setCostCenterList(List<CostCenter> costCenterList) {
    this.costCenterList = costCenterList;
  }

  public BigDecimal getValueTitle() {
    return valueTitle;
  }

  public void setValueTitle(BigDecimal valueTitle) {
    this.valueTitle = valueTitle;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
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
