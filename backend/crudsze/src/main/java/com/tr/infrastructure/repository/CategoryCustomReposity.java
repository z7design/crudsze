package com.tr.infrastructure.repository;

import com.tr.domain.dto.CategoryDTO;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCustomReposity {
  List<CategoryDTO> findCategoryAll(final UUID categoryID);
}
