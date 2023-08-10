package com.tr.api.mappers;

import com.tr.api.Category.CategoryResponse;
import com.tr.domain.Category.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
  public CategoryResponse toResponse(final CategoryEntity entity) {
    return CategoryResponse.builder()
        .categoryId(entity.getCategoryId())
        .name(entity.getName())
        .description(entity.getDescription())
        .build();
  }
  

}
