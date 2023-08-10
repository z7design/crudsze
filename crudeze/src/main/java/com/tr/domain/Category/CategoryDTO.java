package com.tr.domain.Category;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
  @Column(name = "category_id")
  private Long categoryId;

  @Column(name = "name_category")
  private String name;

  @Column(name = "description")
  private String description;

  public CategoryDTO(CategoryEntity entity){
    this.categoryId = entity.getCategoryId();
    this.name = entity.getName();
    this.description = entity.getDescription();
  }
}
