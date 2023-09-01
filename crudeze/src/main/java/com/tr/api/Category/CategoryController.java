package com.tr.api.Category;

import com.tr.domain.Category.CategoryEntity;
import com.tr.domain.Category.CategoryService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController {
  
  @Autowired private CategoryService service;

  @GetMapping
  public List<CategoryEntity> findAllByCategories() {

    return service.findAllByCategories();
  }

  @GetMapping("/{categoryId}")
  public CategoryEntity getByCategoryId(@PathVariable Long categoryId) {
    return service.findCategoryById(categoryId);
  }

  @PutMapping("/{categoryId}")
  public CategoryEntity updateCategory(@PathVariable Long categoryId, @RequestBody CategoryEntity category) {
    CategoryEntity categoryCurrent = service.findCategoryById(categoryId);
    BeanUtils.copyProperties(category, categoryCurrent, "categoryId");
    return service.createCategory(categoryCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryEntity createCategory(@RequestBody CategoryEntity category) {
    return service.createCategory(category);
  }

  @DeleteMapping("/{categoryId}")
  public void deleteCategory(@PathVariable Long categoryId) {

    service.deleteCategory(categoryId);
  }
}
