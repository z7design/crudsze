package com.tr.domain.Acessories;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "acessories")
@Entity
public class AcessoriesEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "aceesory_Id")
  private Long acessoryId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "status")
  private String status;

}
