package com.tr.domain.Titles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tr.domain.CostCenter.CostCenterEntity;
import com.tr.domain.Enums.TypeTitle;
import com.tr.domain.User.UserEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "titles")
public class TitlesEntity {

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
  private UserEntity userEntity;

  @Enumerated(EnumType.STRING)
  private TypeTitle typeTitle;

  @ManyToMany
  @JoinTable(
      name = "titles_cost_center",
      joinColumns = @JoinColumn(name = "title_id"),
      inverseJoinColumns = @JoinColumn(name = "cost_center_id"))
  private List<CostCenterEntity> costCenters;

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
  }
}
