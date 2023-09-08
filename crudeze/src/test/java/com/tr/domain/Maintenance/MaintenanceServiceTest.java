package com.tr.domain.Maintenance;

import static com.tr.domain.Enums.TypeMaintenance.PREVENTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.tr.domain.Enums.TypeMaintenance;
import com.tr.domain.Vehicle.VehicleEntity;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MaintenanceServiceTest {

  @Mock private MaintenanceRepository repository;

  @InjectMocks private MaintenanceService service;
  private VehicleEntity vehicle;
  private MaintenanceEntity maintenance;
  private Long maintenanceId;
  private TypeMaintenance typeMaintenance;
  private LocalDate dateFinish = LocalDate.of(2007, 07, 02);
  private LocalDate dateInitial = LocalDate.of(2007, 07, 02);

  @BeforeEach
  public void setup() {
    maintenanceId = 1L;
    typeMaintenance = PREVENTIVE;

    maintenance = new MaintenanceEntity();
    maintenance.setMaintenanceId(maintenanceId);
    maintenance.setKmInitial("kmInitial");
    maintenance.setKmFinish("kmFinish");
    maintenance.setDateInitial(dateInitial);
    maintenance.setDateFinish(dateFinish);
    maintenance.setDescription("Description");
    maintenance.setTypeMaintenance(typeMaintenance);
    maintenance.setVehicle(vehicle);

    vehicle = new VehicleEntity();
  }

  @Test
  void shouldCreateMaintenance() {

    when(repository.save(any(MaintenanceEntity.class))).thenReturn(maintenance);
    MaintenanceEntity savedMainten = service.createMaintenance(maintenance);

    assertEquals(maintenance, savedMainten);
  }

  @Test
  void findMaintenanceById() {}

  @Test
  void findAllByMaintenance() {}

  @Test
  void updateMaintenance() {}

  @Test
  void deleteMaintenance() {}
}
