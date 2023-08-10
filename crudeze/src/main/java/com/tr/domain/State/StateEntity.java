package com.tr.domain.State;

import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "state")
public class StateEntity {

  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "state_id")
  public Long stateId;

  @Column(name = "name")
  private String name;

  @Column(name = "uf")
  private String uf;
}
