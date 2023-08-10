package com.tr.domain.Maintenance;

import com.tr.domain.Vehicle.VehicleEntity;
import com.tr.domain.Vehicle.VehicleService;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceService {

  private static final String MSG_MAINTENANCE_NOT_FOUND =
      "There is no Maintenance registration with the code %d";
  private static final String MSG_MAINTENANCE_IN_USE =
      "Code Maintenance %d cannot be removed as it is in use";

  @Autowired private MaintenanceRepository repository;
  @Autowired private VehicleService vehicleService;

  @Transactional
  public MaintenanceEntity createMaintenance(final MaintenanceEntity maintenance) {
    Long vehicleId = maintenance.getVehicle().getVehicleId();
    VehicleEntity vehicle = vehicleService.findVehicleById(vehicleId);

    maintenance.setVehicle(vehicle);
    return repository.save(maintenance);
  }

  @Transactional
  public MaintenanceEntity findMaintenanceById(final Long maintenanceId) {
    return repository
        .findById(maintenanceId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(MSG_MAINTENANCE_NOT_FOUND, maintenanceId)));
  }

  public List<MaintenanceEntity> findAllByMaintenance() {
    return repository.findAll();
  }

  @Transactional
  public MaintenanceEntity updateMaintenance(final MaintenanceEntity maintenance) {
    MaintenanceEntity entity =
        repository
            .findById(maintenance.getMaintenanceId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setKmInitial(entity.getKmInitial());
    entity.setKmFinish(entity.getKmFinish());
    entity.setDescription(entity.getDescription());
    entity.setDateInitial(entity.getDateInitial());
    entity.setDateFinish(entity.getDateFinish());
    entity.setVehicle(entity.getVehicle());
    entity.setTypeMaintenance(entity.getTypeMaintenance());
    return repository.save(maintenance);
  }

  public void deleteMaintenance(Long maintenanceId) {
    try {
      repository.deleteById(maintenanceId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_MAINTENANCE_NOT_FOUND, maintenanceId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_MAINTENANCE_IN_USE, maintenanceId));
    }
  }
}
