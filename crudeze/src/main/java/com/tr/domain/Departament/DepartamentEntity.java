package com.tr.domain.Departament;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departament")
public class DepartamentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "departament_id")
  private Long departamentId;

  @Column(name = "name")
  private String name;

  @Column(name = "localy")
  private String localy;

  @Column(name = "description")
  private String description;
}
