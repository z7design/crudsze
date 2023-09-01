package com.tr.domain.Titles;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.tr.domain.CostCenter.CostCenterEntity;
import com.tr.domain.Enums.TypeTitle;
import com.tr.domain.User.UserEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

  // @Column(nullable = false, scale = 2, precision = 3)
  private BigDecimal valueTitle = new BigDecimal(0.2);

  @Column(name = "date_registration")
  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dateRegistration;

  @Column(name = "date_reference")
  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dateReference;

  @Column(name = "due_date")
  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dueDate;

  @Column(name = "date_payment")
  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate datePayment;

  @Column(name = "observation", columnDefinition = "TEXT")
  private String observation;

  @ManyToOne
  @JoinColumn(name = "userEntity")
  private UserEntity userEntity;

  @Enumerated(EnumType.STRING)
  private TypeTitle typeTitle;

  @ManyToMany
  @JoinTable(
      name = "titles_costcenter",
      joinColumns = @JoinColumn(name = "titles_id"),
      inverseJoinColumns = @JoinColumn(name = "costcenter_id"))
  private List<CostCenterEntity> costCenter;
}
