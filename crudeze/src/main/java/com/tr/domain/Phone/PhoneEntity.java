package com.tr.domain.Phone;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phones")
public class PhoneEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "phone_id")
  private Long phoneId;

  @Column(name = "ddd")
  private String ddd;

  @Column(name = "ddi")
  private String ddi;

  @Column(name = "number")
  private Integer number;

}
