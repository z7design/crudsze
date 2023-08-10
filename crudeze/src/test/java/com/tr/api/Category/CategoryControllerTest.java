package com.tr.api.Category;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tr.domain.Category.CategoryEntity;
import com.tr.domain.Category.CategoryService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc
public class CategoryControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper mapper;

  @MockBean private CategoryService service;

  private CategoryEntity category;
  private Long categoryId;

  @BeforeEach
  public void setup() {
    category = new CategoryEntity(categoryId, "Name 1", "Description 1");
  }

  @Test
  void shouldFindAllCategory() throws JsonProcessingException, Exception {

    var category = new CategoryEntity(categoryId, "Name 1", "Description 1");
    category.setName("Nova Categoria");
    category.setDescription("Descrição de Categoria");

    when(service.findAllByCategories()).thenReturn(List.of(category));
    this.mockMvc
        .perform(get("/categories"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json("[{'categoryId':null,name:'Nova Categoria',descricao:'Descrição de Categoria'}]"));
  }

  @Test
  void shouldCreateNewCategory() throws JsonProcessingException, Exception {

   var category = new CategoryEntity(categoryId, "Name 1", "Description 1");
    category.setName("Nova Categoria");
    category.setDescription("Descrição de Categoria");

     when(service.findAllByCategories()).thenReturn(List.of(category));
    this.mockMvc
        .perform(post("/categories"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json("[{'categoryId':null,name:'Nova Categoria',descricao:'Descrição de Categoria'}]"));
  }

  @Test
  void shouldFindByIdCategory() throws JsonProcessingException, Exception {
    Long categoryId = 1L;
   var category = new CategoryEntity(categoryId, "Name 1", "Description 1");

    when(service.findCategoryById(categoryId)).thenReturn(category);

    ResultActions response = mockMvc.perform(get("/categories/{categoryId}", categoryId));
    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.name", is(category.getName())))
        .andExpect(jsonPath("$.description", is(category.getDescription())));
  }

  @Test
  void shouldDeleteCategory() throws JsonProcessingException, Exception {
   Long categoryId = 1L;

    doNothing().when(service).deleteCategory(categoryId);
    ResultActions response = mockMvc.perform(delete("/categories/{categoryId}", categoryId));

    response.andExpect(status().isNoContent()).andDo(print());
  }
}
