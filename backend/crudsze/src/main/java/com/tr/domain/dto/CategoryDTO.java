package com.tr.domain.dto;

import com.tr.domain.entities.Category;
import java.util.UUID;
import javax.persistence.Column;
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
  private UUID categoryId;

  @Column(name = "name_category")
  private String name;

  @Column(name = "description")
  private String description;

  public CategoryDTO(Category entity){
    this.categoryId = entity.getCategoryId();
    this.name = entity.getName();
    this.description = entity.getDescription();
  }
}
