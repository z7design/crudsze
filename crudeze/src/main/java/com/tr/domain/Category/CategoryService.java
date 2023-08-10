package com.tr.domain.Category;

import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private static final String MSG_CATEGORY_NOT_FOUND =
      "There is no category registration with the code %d";
  private static final String MSG_CATEGORY_IN_USE =
      "Code category %d cannot be removed as it is in use";

  @Autowired private final CategoryRepository repository;

  @Transactional
  public CategoryEntity createCategory(CategoryEntity category) {
    return repository.save(category);
  }

  @Transactional
  public List<CategoryEntity> findAllByCategories() {
    return repository.findAll();
  }

  @Transactional
  public CategoryEntity findCategoryById(final Long categoryId) {
    return repository
        .findById(categoryId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_CATEGORY_NOT_FOUND, categoryId)));
  }

  @Transactional
  public CategoryEntity updateCategory(final CategoryEntity category) {
    CategoryEntity entity =
        repository
            .findById(category.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    entity.setDescription(entity.getDescription());

    return repository.save(category);
  }

  public void deleteCategory(Long categoryId) {
    try {
      repository.deleteById(categoryId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_CATEGORY_NOT_FOUND, categoryId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_CATEGORY_IN_USE, categoryId));
    }
  }
}
