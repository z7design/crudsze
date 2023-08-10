package com.tr.api.Vehicle;

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
import com.tr.domain.Document.DocumentEntity;
import com.tr.domain.Document.DocumentService;
import com.tr.domain.Vehicle.VehicleEntity;
import com.tr.domain.exception.ResourceNotFoundException;
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

  private Long vehicleId = 1L;
  private Long documentId = 1L;
  private DocumentEntity document;
  private VehicleEntity vehicle;
  private BigDecimal value = BigDecimal.valueOf(150.00);

  @BeforeEach
  public void setup() {
    vehicle = new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
  }

  @Test
  void shouldFindAllVehicle() throws JsonProcessingException, Exception {

    List<VehicleEntity> vehicles = new ArrayList<>();
    vehicles.add(vehicle);
    vehicles.add(new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document));

    when(service.findAllByVehicle()).thenReturn(vehicles);

    ResultActions response = mockMvc.perform(get("/vehicles"));
    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.size()", is(vehicles.size())));
  }

  @Test
  void shouldCreateNewVehicle() throws JsonProcessingException, Exception {

    when(service.createVehicle(any())).thenReturn(vehicle);
    this.mockMvc.perform(
        post("/vehicles")
            .contentType(APPLICATION_JSON)
            .content(
                "{\"vehicleId\":null,"
                    + "\"plate\":\"GRT-7898\", "
                    + "\"color\":\"Black\", "
                    + "\"model\":\"Chevet\", " 
                    + "\"value\":\"value}")
            .accept(MediaType.APPLICATION_JSON));
  }

  @Test
  void shouldFindByIdVehicle() throws JsonProcessingException, Exception {
    Long vehicleId = 1L;
    when(service.findVehicleById(vehicleId)).thenReturn(vehicle);

    this.mockMvc
        .perform(get("/vehicles/{vehicleId}", vehicleId))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void shouldFindByIdVehicleNotFound() throws JsonProcessingException, Exception {
    Long vehicleId = 1L;
    when(service.findVehicleById(vehicleId)).thenThrow(ResourceNotFoundException.class);

    this.mockMvc
        .perform(get("/vehicles/{vehicleId}", vehicleId))
        .andExpect(status().isNotFound())
        .andDo(print());
  }
  
//Teste de busca de ID inválido, cenario negativo.
  @Test
  void shouldDeleteVehicle() throws JsonProcessingException, Exception {
    Long vehicleId = 1L;
    doNothing().when(service).deleteVehicle(vehicleId);
    ResultActions response = 
        mockMvc.perform(delete("/vehicles/{vehicleId}", vehicleId));

    response.andExpect(status()
        .isNoContent()).andDo(print());
  }

  @Test
  void shouldUpdateVehicle() throws JsonProcessingException, Exception {
    Long vehicleId = 1L;

    when(service.findVehicleById(vehicleId)).thenReturn(vehicle);
    when(service.update(any())).thenReturn(vehicle);

    var vehicle = new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document);

    ResultActions response =
        mockMvc.perform(
            put("/vehicles/{vehicleId}", vehicleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vehicle)));

    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.plate", is(vehicle.getPlate())))
        .andExpect(jsonPath("$.color", is(vehicle.getColor())))
        .andExpect(jsonPath("$.model", is(vehicle.getModel())))
        .andExpect(jsonPath("$.value").value(vehicle.getValue()));
  }

  @Test
  void shouldUpdateVehicleNotFound() throws JsonProcessingException, Exception {
    Long vehicleId = 1L;

    when(service.findVehicleById(vehicleId)).thenThrow(ResourceNotFoundException.class);
    when(service.update(any())).thenReturn(vehicle);

    var vehicle = new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document);

    ResultActions response =
        mockMvc.perform(
            put("/vehicles/{vehicleId}", vehicleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vehicle)));

    response.andExpect(status().isNotFound()).andDo(print());
  }
}
