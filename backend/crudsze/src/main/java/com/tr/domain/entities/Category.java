package com.tr.domain.entities;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Builder
@Data
@Table(name = "category")
public class Category {

  @Id
  @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name = "category_id")
  private UUID categoryId;

  @Column(name = "name")
  private String name;
  
  
  @Column(name = "description")
  private String Description;
  
  public Category(){}

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
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Category))
      return false;
    Category category = (Category) o;
    return getCategoryId().equals(category.getCategoryId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCategoryId());
  }
}
