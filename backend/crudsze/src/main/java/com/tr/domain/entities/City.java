package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "city")
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "city_id")
  private Long cityId;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(nullable = false)
  private State state;

  public Long getCityId() {
    return cityId;
  }

  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
