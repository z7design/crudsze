package com.tr.api.mappers;

import com.tr.api.responses.CategoryResponse;
import com.tr.domain.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
  public CategoryResponse toResponse(final Category entity) {
    return CategoryResponse.builder()
        .categoryId(entity.getCategoryId())
        .name(entity.getName())
        .description(entity.getDescription())
        .build();
  }
  

}
