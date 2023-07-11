package com.tr.domain.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "category")
public class Category implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name = "category_id")
  private UUID categoryId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  public Category() {}

  public Category(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Category(UUID categoryId, String name, String description) {
    this.categoryId = categoryId;
    this.name = name;
    this.description = description;
  }

  public UUID getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(UUID categoryId) {
    this.categoryId = categoryId;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Category)) return false;
    Category category = (Category) o;
    return getCategoryId().equals(category.getCategoryId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCategoryId());
  }
}
