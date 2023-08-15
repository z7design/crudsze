package com.tr.domain.Titles;

import com.tr.domain.CostCenter.CostCenterEntity;
import com.tr.domain.Enums.TypeTitle;
import com.tr.domain.User.UserEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  @Enumerated(EnumType.STRING)
  private TypeTitle typeTitle;

  @ManyToMany
  @JoinTable(
      name = "titles_costcenter",
      joinColumns = @JoinColumn(name = "titles_id"),
      inverseJoinColumns = @JoinColumn(name = "costcenter_id"))
  private List<CostCenterEntity> costCenters;

}
