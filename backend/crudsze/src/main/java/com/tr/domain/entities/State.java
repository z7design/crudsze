package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
@Table(name = "state")
public class State {

  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "state_id")
  public Long stateId;

  @Column(name = "name")
  private String name;

  @Column(name = "uf")
  private String uf;

  public State() {}

  public State(Long stateId, String name, String uf) {
    this.stateId = stateId;
    this.name = name;
    this.uf = uf;
  }

  public State(String name, String uf) {
    this.name = name;
    this.uf = uf;
  }

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

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof State)) return false;
    State state = (State) o;
    return getStateId().equals(state.getStateId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getStateId());
  }
}
