package com.tr.domain.State;

import javax.persistence.*;
import com.tr.domain.User.UserEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "state")
public class StateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "state_id")
  public Long stateId;

  @Column(name = "name")
  private String name;

  @Column(name = "uf")
  private String uf;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;
}
