package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "state")
public class State {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "state_id")
  public Long stateId;

  @Column(name = "name", updatable = false, nullable = false)
  private String name;

  @Column(name = "sigle", updatable = false, nullable = false)
  private String sigle;

  public Long getStateId() {
    return stateId;
  }

  public void setStateId(Long stateId) {
    this.stateId = stateId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSigle() {
    return sigle;
  }

  public void setSigle(String sigle) {
    this.sigle = sigle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof State))
      return false;
    State state = (State) o;
    return getStateId().equals(state.getStateId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getStateId());
  }
}
