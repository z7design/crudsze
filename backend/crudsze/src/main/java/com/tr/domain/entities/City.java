package com.tr.domain.entities;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "city")
public class City {

  @Id
  @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name = "city_id", updatable = false, nullable = false)
  private UUID cityId;

  @Column(name = "name")
  private String name;
  @ManyToOne
  private State state;
  
  public UUID getCityId() {
    return cityId;
  }

  public void setCityId(UUID cityId) {
    this.cityId = cityId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof City))
      return false;
    City city = (City) o;
    return getCityId().equals(city.getCityId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCityId());
  }
}
