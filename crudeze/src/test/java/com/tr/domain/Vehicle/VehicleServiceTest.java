package com.tr.domain.Vehicle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tr.domain.Document.DocumentEntity;
import com.tr.domain.Document.DocumentService;
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

  private VehicleEntity vehicle;
  private DocumentEntity document;
  private BigDecimal value = BigDecimal.valueOf(150.000);

  @BeforeEach
  public void setup() {
    VehicleEntity vehicle =
        new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
  }

  @Test
  public void shouldVeicleWhenThenSalveVeicles() {
    vehicle = new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
    
    when(repository.save(any(VehicleEntity.class))).thenReturn(vehicle);
    VehicleEntity savedVeic = service.createVehicle(vehicle);
    
    assertEquals(vehicle, savedVeic);
  }

  @Test
  void shouldFindAllByVehicles() {
    vehicle = new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
    VehicleEntity vehicle1 =
        new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document);

    when(repository.findAll()).thenReturn(List.of(vehicle, vehicle1));
    List<VehicleEntity> listVehicle = service.findAllByVehicle();
    assertNotNull(listVehicle);
    assertEquals(2, listVehicle.size());
  }

  @Test
  void shouldFindAllThenReturnEmptyVehicles() {
    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<VehicleEntity> listVehicle = repository.findAll();
    assertTrue(listVehicle.isEmpty());
    assertEquals(0, listVehicle.size());
  }

  @Test
  void shouldFindByIdViecle() {
    final var vehicle = new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
    when(repository.findById(any())).thenReturn(Optional.ofNullable(vehicle));
    VehicleEntity savedVehicle = service.findVehicleById(vehicleId);
    assertNotNull(savedVehicle);
    assertEquals(vehicle.getVehicleId(), savedVehicle.getVehicleId());
  }

  @Test
  void ShoulUpdateVeicle() {
    final var vehicle = new VehicleEntity(vehicleId, "GRT-7898", "Corolla", "Red", value, document);
    when(repository.findById(any())).thenReturn(Optional.of(vehicle));

    vehicle.setModel("Corolla");
    vehicle.setPlate("GHF-2546");
    vehicle.setColor("Red");
    vehicle.setValue(value);

    when(repository.save(vehicle)).thenReturn(vehicle);
    VehicleEntity updateVehicle = service.update(vehicle);

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
