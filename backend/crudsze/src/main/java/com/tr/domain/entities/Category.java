package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long categoryId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  public Category() {}

  public Category(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Category(Long categoryId, String name, String description) {
    this.categoryId = categoryId;
    this.name = name;
    this.description = description;
  }

  public Long getCategoryId() {

    return categoryId;
  }

  public void setCategoryId(Long categoryId) {

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
