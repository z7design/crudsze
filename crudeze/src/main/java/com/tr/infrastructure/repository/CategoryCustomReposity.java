package com.tr.infrastructure.repository;

import com.tr.domain.Category.CategoryDTO;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCustomReposity {
  List<CategoryDTO> findById(final UUID categoryID);
}
