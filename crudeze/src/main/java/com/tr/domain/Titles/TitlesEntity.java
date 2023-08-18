package com.tr.domain.Titles;

import com.tr.domain.CostCenter.CostCenterEntity;
import com.tr.domain.Enums.TypeTitle;
import com.tr.domain.User.UserEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "titles")
public class TitlesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "titles_id")
  private Long titlesId;

  @Column(name = "name")
  private String name;

  @Column(nullable = false)
  private String description;

  //@Column(nullable = false, scale = 2, precision = 3)
  private BigDecimal valueTitle = new BigDecimal(0.2);

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
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  @Enumerated(EnumType.STRING)
  private TypeTitle typeTitle;

  @ManyToMany
  @JoinTable(
      name = "titles_costcenter",
      joinColumns = @JoinColumn(name = "titles_id"),
      inverseJoinColumns = @JoinColumn(name = "costcenter_id"))
  private List<CostCenterEntity> costCenter;

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

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  public TypeTitle getTypeTitle() {
    return typeTitle;
  }

  public void setTypeTitle(TypeTitle typeTitle) {
    this.typeTitle = typeTitle;
  }

  public List<CostCenterEntity> getCostCenter() {
    return costCenter;
  }

  public void setCostCenter(List<CostCenterEntity> costCenter) {
    this.costCenter = costCenter;
  }
}
