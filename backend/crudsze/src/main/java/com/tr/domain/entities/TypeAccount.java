package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "type_account")
public class TypeAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "type_account_id")
  private Long typeAccountId;

  @Column(name = "description")
  private String description;

  public Long getTypeAccountId() {
    return typeAccountId;
  }

  public void setTypeAccountId(Long typeAccountId) {
    this.typeAccountId = typeAccountId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TypeAccount)) return false;
    TypeAccount that = (TypeAccount) o;
    return getTypeAccountId().equals(that.getTypeAccountId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTypeAccountId());
  }
}
