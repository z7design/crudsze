package com.tr.domain.services;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tr.domain.entities.Category;
import com.tr.domain.repositories.CategoryRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
  private static final UUID categoryId = UUID.randomUUID();
  @Mock
  private TestEntityManager manager;
  @InjectMocks
  private CategoryService service;
  @Mock
  private CategoryRepository repository;

   @Test
  public void shouldCreateCategories() {
    Category category = new Category();
    
    when(repository.save(any(Category.class))).thenReturn(category);
    Category savedCat = service.createCategory(category);
    assertEquals(category, savedCat);
    
  }

  @Test
  void findAllByCategories() {
    Category category = new Category();
    
    when(repository.findById(categoryId)).thenReturn(Optional.of(category));
    Optional<Category> result = service.buscarOuFalhar();

    assertTrue(result.isPresent());
    assertEquals(category, result.get());
  }

  @Test
  void updateCategory() {
    
    
    }

  @Test
  public void deleteShouldDoNothingWhenIdExists() {
    assertDoesNotThrow(() -> {
      service.deleteCategory(categoryId);
    });
      verify(repository).deleteById(categoryId);
    }
  
}