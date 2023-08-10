package com.tr.api.Supplier;

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
import com.tr.domain.Address.AddressEntity;
import com.tr.domain.Address.AddressService;
import com.tr.domain.Supplier.SupplierEntity;
import com.tr.domain.Supplier.SupplierService;
import com.tr.domain.exception.ResourceNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(SupplierController.class)
@AutoConfigureMockMvc
public class SupplierControllerTest {

  @InjectMocks SupplierController supplierController;
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper mapper;
  @MockBean private SupplierService service;
  @MockBean private AddressService addressService;
  private SupplierEntity supplier;
  private Long supplierId = 1L;
  private AddressEntity address;

  @BeforeEach
  public void setup() {
    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    var supplier =
        new SupplierEntity(
            supplierId,
            "Clear Industria e comercia Ltda",
            "31873559000150",
            "Olympus Turismo Ltda",
            "Wanderson",
            "OlympusTur",
            "3095442146449",
            "3095442146449",
            "3135117899",
            "31998555677",
            dateRegistration,
            dateOfLastPurchase,
            address);
  }

  @Test
  void shouldFindAllSupplier() throws JsonProcessingException, Exception {

    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    List<SupplierEntity> suppliers = new ArrayList<>();
    suppliers.add(supplier);
    suppliers.add(
        new SupplierEntity(
            supplierId,
            "Clear Industria e comercia Ltda",
            "31873559000150",
            "Olympus Turismo Ltda",
            "Wanderson",
            "OlympusTur",
            "3095442146449",
            "3095442146449",
            "3135117899",
            "31998555677",
            dateRegistration,
            dateOfLastPurchase,
            address));

    when(service.findAllBySupplier()).thenReturn(suppliers);

    ResultActions response = mockMvc.perform(get("/suppliers"));
    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.size()", is(suppliers.size())));
  }

  @Test
  void shouldCreateNewSupplier() throws JsonProcessingException, Exception {

    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    when(service.createSupplier(any())).thenReturn(supplier);
    this.mockMvc.perform(
        post("/suppliers")
            .contentType(APPLICATION_JSON)
            .content(
                "{\"supplierId\":null,"
                    + "\"dateRegistration\":\"2023-07-27\","
                    + "\"dateOfLastPurchase\":\"2023-07-27\", "
                    + "\"cnpj\":\"31873559000150\","
                    + "\"name\":\"Olympus Turismo\","
                    + "\"corpareteName\":\"Olympus Turismo Ltda\","
                    + "\"responsable\":\"Wanderson\","
                    + "\"fantasyName\":\"OlympusTur\","
                    + "\"email\":\"olympustur@gmail.com\","
                    + "\"municipalRegistration\":\"3095442146449\", "
                    + "\"phone\":\"3135117899\", "
                    + "\"celphone\":\"31998555677\","
                    + "\"address\":\"address}")
            .accept(MediaType.APPLICATION_JSON));
  }

  @Test
  void shouldFindByIdSupplier() throws JsonProcessingException, Exception {
    Long supplierId = 1L;
    when(service.findSupplierById(supplierId)).thenReturn(supplier);

    this.mockMvc
        .perform(get("/suppliers/{supplierId}", supplierId))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void shouldFindByIdSupplierNotFound() throws JsonProcessingException, Exception {
    Long supplierId = 1L;
    when(service.findSupplierById(supplierId)).thenThrow(ResourceNotFoundException.class);

    this.mockMvc
        .perform(get("/suppliers/{supplierId}", supplierId))
        .andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  void shouldDeleteSupplier() throws JsonProcessingException, Exception {
    Long supplierId = 1L;
    doNothing().when(service).deleteSupplier(supplierId);
    ResultActions response = mockMvc.perform(delete("/suppliers/{supplierId}", supplierId));

    response.andExpect(status().isNoContent()).andDo(print());
  }

  @Test
  void shouldUpdateSupplier() throws JsonProcessingException, Exception {

    Long supplierId = 1L;
    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    SupplierEntity updateSuplier =
        new SupplierEntity(
            supplierId,
            "Clear Industria e comercio Ltda",
            "31873559000150",
            "Olympus Turismo Ltda",
            "Wanderson",
            "OlympusTur",
            "3095442146449",
            "3095442146449",
            "3135117899",
            "31998555677",
            dateRegistration,
            dateOfLastPurchase,
            address);

    when(service.findSupplierById(supplierId)).thenReturn(supplier);
    when(service.updateSupplier(any())).thenReturn(updateSuplier);

    ResultActions response =
        mockMvc.perform(
            put("/suppliers/{supplierId}", supplierId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(supplier)));

    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.name", is(supplier.getName())))
        .andExpect(jsonPath("$.cnpj", is(supplier.getCnpj())))
        .andExpect(jsonPath("$.corpareteName", is(supplier.getCorpareteName())))
        .andExpect(jsonPath("$.fantasyName", is(supplier.getFantasyName())))
        .andExpect(jsonPath("$.responsable", is(supplier.getResponsable())))
        .andExpect(jsonPath("$.email", is(supplier.getEmail())))
        .andExpect(jsonPath("$.phone", is(supplier.getPhone())))
        .andExpect(jsonPath("$.celphone", is(supplier.getCelphone())))
        .andExpect(jsonPath("$.municipalRegistration", is(supplier.getMunicipalRegistration())))
        .andExpect(jsonPath("$.dateRegistration", is(supplier.getDateRegistration())))
        .andExpect(jsonPath("$.dateOfLastPurchase", is(supplier.getDateOfLastPurchase())))
        .andExpect(jsonPath("$.address").value(supplier.getAddress()));
  }

  @Test
  void shouldUpdateSupplierNotFound() throws JsonProcessingException, Exception {
    Long supplierId = 1L;
    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    when(service.findSupplierById(supplierId)).thenReturn(supplier);

    var supplier =
        new SupplierEntity(
            supplierId,
            "Clear Industria e comercia Ltda",
            "31873559000150",
            "Olympus Turismo Ltda",
            "Wanderson",
            "OlympusTur",
            "3095442146449",
            "3095442146449",
            "3135117899",
            "31998555677",
            dateRegistration,
            dateOfLastPurchase,
            address);

    ResultActions response =
        mockMvc.perform(
            put("/suppliers/{supplierId}", supplierId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(supplier)));

    response.andExpect(status().isNotFound()).andDo(print());
  }
}
