package com.tr.domain.State;

import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateDTO {
  @Column(name = "state_id", updatable = false, nullable = false)
  public UUID stateId;

  @Column(name = "name", updatable = false, nullable = false)
  private String name;

  @Column(name = "uf", updatable = false, nullable = false)
  private String uf;

  public StateDTO(StateEntity state, String uf) {
    this.name = state.getName();
    this.uf = state.getUf();
  }
}
