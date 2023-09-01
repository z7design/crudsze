package com.tr.domain.Category;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

  @InjectMocks private CategoryService service;
  @Mock private CategoryRepository repository;

  private Long categoryId;
  private CategoryEntity category;

  @BeforeEach
  public void setup() {
    CategoryEntity category = new CategoryEntity(categoryId, "Name 1", "Description 1");
  }

  @Test
  public void shouldCategoryWhenThenSalveCategories() {
    category = new CategoryEntity(categoryId, "Despesasas agragado", "despesas com agregado");

    when(repository.save(any(CategoryEntity.class))).thenReturn(category);
    CategoryEntity savedCat = service.createCategory(category);
    assertEquals(category, savedCat);
  }

  @Test
  void shouldFindAllByCategories() {
    category = new CategoryEntity(categoryId, "Despesasas agragado", "despesas com agregado");
    CategoryEntity category1 = new CategoryEntity(categoryId, "Despesasas Marketing", "despesas com Marketing");

    List<CategoryEntity> value = new java.util.ArrayList<>();
    value.add(category);
    value.add(category1);    when(repository.findAll()).thenReturn(value);
    List<CategoryEntity> listCategory = service.findAllByCategories();
    assertNotNull(listCategory);
    assertEquals(2, listCategory.size());
  }

  @Test
  void shouldFindAllThenReturnEmptyCategories() {
    ;
    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<CategoryEntity> listCategory = repository.findAll();
    assertTrue(listCategory.isEmpty());
    assertEquals(0, listCategory.size());
  }

  @Test
  void shouldCategoryFindById() {
    category = new CategoryEntity(categoryId, "Despesas", "Despesas geral");
    when(repository.findById(any())).thenReturn(Optional.of(category));
    CategoryEntity savedCategory = service.findCategoryById(categoryId);
    assertNotNull(savedCategory);
    assertEquals(category.getCategoryId(), savedCategory.getCategoryId());
  }

  @Test
  void updateCategory() {
    category = new CategoryEntity(categoryId, "Despesasas agragado", "despesas com agregado");
    when(repository.findById(any())).thenReturn(Optional.of(category));

    category.setName("Despesas Veiculo");
    category.setDescription("Despesas com Veiculo");

    when(repository.save(category)).thenReturn(category);
    CategoryEntity updateCategory = service.updateCategory(category);

    assertNotNull(updateCategory);
    assertEquals(category.getCategoryId(), updateCategory.getCategoryId());
    assertEquals(category.getDescription(), updateCategory.getDescription());
  }

  @Test
  public void deleteShouldDoNothingWhenIdExists() {
    assertDoesNotThrow(
        () -> {
          service.deleteCategory(categoryId);
        });
    verify(repository).deleteById(categoryId);
  }
}
