package com.tr.api.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tr.domain.entities.State;
import com.tr.domain.services.StateService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(StateController.class)
@AutoConfigureMockMvc
public class StateControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper mapper;

  @MockBean private StateService service;

  private State state;

  @BeforeEach
  public void setup() {
    state = new State("Novo Estado", "NE");
  }

  @Test
  void shouldFindAllStates() throws JsonProcessingException, Exception {

    var state = new State();
    state.setName("Novo Estado");
    state.setUf("NE");

    when(service.findAllByStates()).thenReturn(List.of(state));
    this.mockMvc
        .perform(get("/states"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json("[{'stateId':null,name:'Novo Estado',uf:'NE'}]"));
  }

  @Test
  void shouldCreateNewState() throws JsonProcessingException, Exception {

    var state = new State();
    state.setName("Novo Estado");
    state.setUf("NE");

    when(service.createState(any())).thenReturn(state);
    this.mockMvc.perform(
        post("/states")
            .contentType(APPLICATION_JSON)
            .content("{\"statId\":null,\"name\":\"Novo Estado\",\"uf\":\"NE}")
            .accept(MediaType.APPLICATION_JSON));
  }

  @Test
  void shouldFindByIdState() throws JsonProcessingException, Exception {
    Long stateId = 1L;
    var state = new State(stateId, "Novo Estado", "NE");

    when(service.findStateById(stateId)).thenReturn(state);

    ResultActions response = mockMvc.perform(get("/states/{stateId}", stateId));
    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.name", is(state.getName())))
        .andExpect(jsonPath("$.uf", is(state.getUf())));
  }

  @Test
  void shouldDeleteState() throws JsonProcessingException, Exception {
    Long stateId = 1L;

    doNothing().when(service).deleteState(stateId);
    ResultActions response = mockMvc.perform(delete("/states/{stateId}", stateId));

    response.andExpect(status().isNoContent()).andDo(print());
  }
}
