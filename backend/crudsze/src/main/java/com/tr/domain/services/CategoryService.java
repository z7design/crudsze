package com.tr.domain.services;

import com.tr.domain.entities.Category;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.repositories.CategoryRepository;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private static final String MSG_CATEGORY_NOT_FOUND =
      "There is no category registration with the code %d";
  private static final String MSG_CATEGORY_IN_USE =
      "Code category %d cannot be removed as it is in use";
  @Autowired 
  private final CategoryRepository repository;
  
  @Autowired
  private EntityManager manager;
  
  public Category createCategory(Category category) {
    return repository.save(category);
  }

  public List<Category> findAllByCategories() {
    return repository.findAll();
  }
  
  @Transactional
  public Category updateCategory(final Category category){
    return manager.merge(category);
  }
  
  @DeleteMapping("/{categoryId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(UUID categoryId) {
    try {
      repository.deleteById(categoryId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_CATEGORY_NOT_FOUND, categoryId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_CATEGORY_IN_USE, categoryId));
    }
  }

  public Category buscarOuFalhar(UUID categoryId) {
    return repository
        .findById(categoryId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_CATEGORY_NOT_FOUND, categoryId)));
  }
}
