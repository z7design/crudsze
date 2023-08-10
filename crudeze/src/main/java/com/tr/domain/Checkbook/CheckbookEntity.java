package com.tr.domain.Checkbook;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "checkbook")
public class CheckbookEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "checkbook_id")
  private Long checkbookId;
  @Column(name = "number_talon")
  private Integer numberTalon;
  @Column(name = "status")
  private String status;

  @Column(name = "emission_date")
  private Date emissionDate;
}
