package com.tr.domain.Vehicle;

import com.tr.domain.Document.DocumentEntity;
import com.tr.domain.Document.DocumentService;
import com.tr.domain.Maintenance.MaintenanceService;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleService {
  private static final String MSG_VEHICLE_NOT_FOUND =
      "There is no veicle registration with the code %d";
  private static final String MSG_VEHICLE_IN_USE =
      "Code veicle %d cannot be removed as it is in use";

  @Autowired private VehicleRepository repository;
  @Autowired private DocumentService documentService;
  @Autowired private MaintenanceService maintenanceService;

  @Transactional
  public VehicleEntity createVehicle(VehicleEntity vehicle) {
    Long documentId = vehicle.getDocument().getDocumentId();
    DocumentEntity document = documentService.findDocumentById(documentId);

    vehicle.setDocument(document);
    return repository.save(vehicle);
  }

  @Transactional
  public VehicleEntity findVehicleById(final Long vehicleId) {
    return repository
        .findById(vehicleId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_VEHICLE_NOT_FOUND, vehicleId)));
  }

  @Transactional
  public VehicleEntity update(final VehicleEntity vehicle) {
    VehicleEntity entity =
        repository
            .findById(vehicle.getVehicleId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setPlate(entity.getPlate());
    entity.setColor(entity.getColor());
    entity.setModel(entity.getModel());
    entity.setValue(entity.getValue());

    return repository.save(vehicle);
  }

  public void deleteVehicle(Long vehicleId) {
    try {
      repository.deleteById(vehicleId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_VEHICLE_NOT_FOUND, vehicleId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_VEHICLE_IN_USE, vehicleId));
    }
  }

  @Transactional
  public List<VehicleEntity> findAllByVehicle() {
    return repository.findAll();
  }
}
