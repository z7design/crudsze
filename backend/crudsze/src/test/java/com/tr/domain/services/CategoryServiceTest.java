package com.tr.domain.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tr.domain.entities.Category;
import com.tr.domain.repositories.CategoryRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
  private static final UUID categoryId = UUID.randomUUID();

  @InjectMocks private CategoryService service;
  @Mock private CategoryRepository repository;

  private Category category;

  @BeforeEach
  public void setup() {
    Category category = new Category(categoryId, "Name 1", "Description 1");
  }

  @Test
  public void shouldCategoryWhenThenSalveCategories() {
    category = new Category(categoryId, "Despesasas agragado", "despesas com agregado");

    when(repository.save(any(Category.class))).thenReturn(category);
    Category savedCat = service.createCategory(category);
    assertEquals(category, savedCat);
  }

  @Test
  void shouldFindAllByCategories() {
    category = new Category(categoryId, "Despesasas agragado", "despesas com agregado");
    Category category1 = new Category(categoryId, "Despesasas Marketing", "despesas com Marketing");

    when(repository.findAll()).thenReturn(List.of(category, category1));
    List<Category> listCategory = service.findAllByCategories();
    assertNotNull(listCategory);
    assertEquals(2, listCategory.size());
  }

  @Test
  void shouldFindAllThenReturnEmptyCategories() {
    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<Category> listCategory = repository.findAll();
    assertTrue(listCategory.isEmpty());
    assertEquals(0, listCategory.size());
  }

  @Test
  void findById() {
    when(repository.findById(any())).thenReturn(Optional.of(category));
    Category savedCategory = service.findCategoryById(categoryId);
    assertNotNull(savedCategory);
    assertEquals(category.getCategoryId(), savedCategory.getCategoryId());
  }

  @Test
  void updateCategory() {
    final var category = new Category(categoryId, "Despesasas agragado", "despesas com agregado");
    when(repository.findById(any())).thenReturn(Optional.of(category));

    category.setName("Despesas Veiculo");
    category.setDescription("Despesas com Veiculo");

    when(repository.save(category)).thenReturn(category);
    Category updateCategory = service.updateCategory(category);

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
