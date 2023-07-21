package com.tr.domain.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "departament")
public class Departament {
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

  public Departament() {}

  public Departament(Long departamentId, String name, String localy, String description) {
    this.departamentId = departamentId;
    this.name = name;
    this.localy = localy;
    this.description = description;
  }

  public Departament(String name, String localy, String description) {
    this.name = name;
    this.localy = localy;
    this.description = description;
  }

  public Long getDepartamentId() {
    return departamentId;
  }

  public void setDepartamentId(Long departamentId) {
    this.departamentId = departamentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocaly() {
    return localy;
  }

  public void setLocaly(String localy) {
    this.localy = localy;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
