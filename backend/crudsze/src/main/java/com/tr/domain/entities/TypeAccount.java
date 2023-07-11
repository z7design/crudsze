package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "type_account")
public class TypeAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "type_account_id")
  private Long typeAccountId;

  @Column(name = "description")
  private String description;

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
