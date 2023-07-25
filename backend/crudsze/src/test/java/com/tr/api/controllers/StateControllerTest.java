package com.tr.api.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tr.domain.entities.State;
import com.tr.domain.services.StateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest
public class StateControllerTest {

  @Autowired 
  private MockMvc mockMvc;

  @Autowired 
  private ObjectMapper mapper;

  @MockBean 
  private StateService service;
  
  private State state;

  @BeforeEach
  public void setup(){
    state = new State("Novo Estado", "NE");
  }

  @Test
  void createState() throws JsonProcessingException, Exception {
    // Given / Arrange
    given(service.createState(any(State.class)))
        .willAnswer((invocation) -> invocation.getArgument(0));

    // When / Act
    ResultActions response =
        mockMvc
            .perform(post("/states")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(state)));

    // Then / Assert
    response
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(state.getName())))
        .andExpect(jsonPath("$.uf", is(state.getUf())));
  }
}
