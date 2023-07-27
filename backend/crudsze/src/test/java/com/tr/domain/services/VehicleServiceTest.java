package com.tr.domain.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tr.domain.entities.Document;
import com.tr.domain.entities.Vehicle;
import com.tr.domain.repositories.VehicleRepository;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

  @InjectMocks private VehicleService service;
  @Mock private VehicleRepository repository;
  @Mock private DocumentService documentService;

  private Long vehicleId = 1L;

  private Vehicle vehicle;
  private Document document;
  private BigDecimal value = BigDecimal.valueOf(150.000);

  @BeforeEach
  public void setup() {
    Vehicle vehicle = new Vehicle(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
  }

  @Test
  public void shouldVeicleWhenThenSalveVeicles() {
    vehicle = new Vehicle(vehicleId, "GRT-7898", "Corolla", "Red", value, document);

    when(repository.save(any(Vehicle.class))).thenReturn(vehicle);
    var savedVeic = service.createVehicle(vehicle);
    assertEquals(vehicle, savedVeic);
  }

  @Test
  void shouldFindAllByVehicles() {
    vehicle = new Vehicle(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
    Vehicle vehicle1 = new Vehicle(vehicleId, "GRT-7898", "Corolla", "Red", value, document);

    when(repository.findAll()).thenReturn(List.of(vehicle, vehicle1));
    List<Vehicle> listVehicle = service.findAllByVehicle();
    assertNotNull(listVehicle);
    assertEquals(2, listVehicle.size());
  }

  @Test
  void shouldFindAllThenReturnEmptyVehicles() {
    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<Vehicle> listVehicle = repository.findAll();
    assertTrue(listVehicle.isEmpty());
    assertEquals(0, listVehicle.size());
  }

  @Test
  void shouldFindByIdViecle() {
    final var vehicle = new Vehicle(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
    when(repository.findById(any())).thenReturn(Optional.ofNullable(vehicle));
    Vehicle savedVehicle = service.findVehicleById(vehicleId);
    assertNotNull(savedVehicle);
    assertEquals(vehicle.getVehicleId(), savedVehicle.getVehicleId());
  }

  @Test
  void ShoulUpdateVeicle() {
    final var vehicle = new Vehicle(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
    when(repository.findById(any())).thenReturn(Optional.of(vehicle));

    vehicle.setModel("Corolla");
    vehicle.setPlate("GHF-2546");
    vehicle.setColor("Red");
    vehicle.setValue(value);

    when(repository.save(vehicle)).thenReturn(vehicle);
    Vehicle updateVehicle = service.update(vehicle);

    assertNotNull(updateVehicle);
    assertEquals(vehicle.getVehicleId(), updateVehicle.getVehicleId());
    assertEquals(vehicle.getPlate(), updateVehicle.getPlate());
    assertEquals(vehicle.getModel(), updateVehicle.getModel());
    assertEquals(vehicle.getColor(), updateVehicle.getColor());
    assertEquals(vehicle.getValue(), updateVehicle.getValue());
  }

  @Test
  public void deleteShouldDoNothingWhenIdExists() {
    assertDoesNotThrow(
        () -> {
          service.deleteVehicle(vehicleId);
        });
    verify(repository).deleteById(vehicleId);
  }
}
