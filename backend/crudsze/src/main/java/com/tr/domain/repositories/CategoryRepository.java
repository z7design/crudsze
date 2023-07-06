package com.tr.domain.repositories;

import com.tr.domain.entities.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
  // List<CategoryEntity> nameCategory(String nameCategory);

}
