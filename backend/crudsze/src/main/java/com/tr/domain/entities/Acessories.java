package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "acessories")
public class Acessories {
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

  public Acessories() {}

  public Acessories(Long acessoryId, String name, String description, String status) {
    this.acessoryId = acessoryId;
    this.name = name;
    this.description = description;
    this.status = status;
  }

  public Acessories(String name, String description, String status) {
    this.name = name;
    this.description = description;
    this.status = status;
  }

  public Long getAcessoryId() {
    return acessoryId;
  }

  public void setAcessoryId(Long acessoryId) {
    this.acessoryId = acessoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Acessories)) return false;
    Acessories that = (Acessories) o;
    return getAcessoryId().equals(that.getAcessoryId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAcessoryId());
  }
}
