package com.tr.api.controllers;

import com.tr.domain.entities.Category;
import com.tr.domain.services.CategoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
  
  @Autowired private CategoryService service;

  @GetMapping
  public List<Category> findAllByCategories() {

    return service.findAllByCategories();
  }

  @GetMapping("/{categoryId}")
  public Category getByCategoryId(@PathVariable Long categoryId) {
    return service.findCategoryById(categoryId);
  }

  @PutMapping("/{categoryId}")
  public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
    Category categoryCurrent = service.findCategoryById(categoryId);
    BeanUtils.copyProperties(category, categoryCurrent, "categoryId");
    return service.createCategory(categoryCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Category createCategory(@RequestBody Category category) {
    return service.createCategory(category);
  }

  @DeleteMapping("/{categoryId}")
  public void deleteCategory(@PathVariable Long categoryId) {

    service.deleteCategory(categoryId);
  }
}
