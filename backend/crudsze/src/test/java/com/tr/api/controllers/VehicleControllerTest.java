package com.tr.api.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tr.domain.entities.Document;
import com.tr.domain.entities.Vehicle;
import com.tr.domain.services.DocumentService;
import com.tr.domain.services.VehicleService;
import java.math.BigDecimal;
import java.util.ArrayList;
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

@WebMvcTest(VehicleController.class)
@AutoConfigureMockMvc
public class VehicleControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper mapper;

  @MockBean private VehicleService service;

  @MockBean private DocumentService documentService;

  private Vehicle vehicle;
  private Long vehicleId = 1L;
  private Document document;
  private BigDecimal value = BigDecimal.valueOf(150.000);

  @BeforeEach
  public void setup() {
    vehicle = new Vehicle(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
  }

  @Test
  void shouldFindAllVehicle() throws JsonProcessingException, Exception {

    List<Vehicle> vehicles = new ArrayList<>();
    vehicles.add(vehicle);
    vehicles.add(new Vehicle(vehicleId, "GRT-7898", "Corolla", "Red", value, document));

    when(service.findAllByVehicle()).thenReturn(vehicles);

    ResultActions response = mockMvc.perform(get("/vehicles"));
    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.size()", is(vehicles.size())));
  }

  @Test
  void shouldCreateNewVehicle() throws JsonProcessingException, Exception {
    Vehicle vehicle = new Vehicle("GRT-7898", "Corolla", "Red", value, document);
    
    when(service.createVehicle(any(Vehicle.class))).thenReturn(vehicle);

    ResultActions response =
        mockMvc.perform(
            post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vehicle)));

    response
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.plate", is(vehicle.getPlate())))
        .andExpect(jsonPath("$.color", is(vehicle.getColor())))
        .andExpect(jsonPath("$.model", is(vehicle.getModel())))
        .andExpect(jsonPath("$.value", is(vehicle.getValue())))
        .andExpect(jsonPath("$.document", is(vehicle.getDocument())));
  }

  @Test
  void shouldFindByIdVehicle() throws JsonProcessingException, Exception {
    Long vehicleId = 1L;

    when(service.findVehicleById(vehicleId)).thenReturn(vehicle);

    ResultActions response = mockMvc.perform(get("/vehicles/{vehicleId}", vehicleId));
    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.plate", is(vehicle.getPlate())))
        .andExpect(jsonPath("$.color", is(vehicle.getColor())))
        .andExpect(jsonPath("$.model", is(vehicle.getModel())))
        .andExpect(jsonPath("$.value", is(vehicle.getValue())))
        .andExpect(jsonPath("$.document", is(vehicle.getDocument())));
  }

  @Test
  void shouldDeleteVehicle() throws JsonProcessingException, Exception {
    Long vehicleId = 1L;

    doNothing().when(service).deleteVehicle(vehicleId);
    ResultActions response = mockMvc.perform(delete("/vehicle/{vehicleId}", vehicleId));

    response.andExpect(status().isNoContent()).andDo(print());
  }
}
