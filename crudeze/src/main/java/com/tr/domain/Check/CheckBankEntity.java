package com.tr.domain.Check;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "check_bank")
public class CheckBankEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "check_id")
  private Long checkId;

  @Column(name = "number")
  private Integer number;

  @Column(name = "nominal_to")
  private String nominalTo;

  @Column(name = "value")
  private BigDecimal value;

  @Column(name = "status")
  private String status;

  @Column(name = "date_emission")
  private Date dateEmission;

  @Column(name = "date_compensation")
  private Date dateCompensation;

  @Column(name = "bomPara")
  private String bomPara;

  @Column(name = "date_status")
  private Date dateStatus;
}
