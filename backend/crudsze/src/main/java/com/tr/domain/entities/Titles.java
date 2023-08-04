package com.tr.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tr.domain.enums.TypeTitle;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity(name = "titles")
@Data
@Table(name = "titles")
public class Titles {

  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "titles_id")
  private Long titlesId;

  @Column(name = "name")
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Double valueTitle;

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

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "user_id")
  private User user;

  @Enumerated(EnumType.STRING)
  private TypeTitle typeTitle;

  @ManyToMany
  @JoinTable(
      name = "titles_cost_center",
      joinColumns = @JoinColumn(name = "title_id"),
      inverseJoinColumns = @JoinColumn(name = "cost_center_id"))
  private List<CostCenter> costCenters;

  public Titles(
      Long titlesId,
      String name,
      String description,
      User user,
      TypeTitle typeTitle,
      Double valueTitle,
      Date dateRegistration,
      Date dateReference,
      Date dueDate,
      Date datePayment,
      String observation) {
    this.titlesId = titlesId;
    this.name = name;
    this.description = description;
    this.user = user;
    this.typeTitle = typeTitle;
    this.valueTitle = valueTitle;
    this.dateRegistration = dateRegistration;
    this.dateReference = dateReference;
    this.dueDate = dueDate;
    this.datePayment = datePayment;
    this.observation = observation;
  }

  public Titles() {}

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
}
