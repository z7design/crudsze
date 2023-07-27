package com.tr.domain.services;

import com.tr.domain.entities.Vehicle;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.VehicleRepository;
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

  @Transactional
  public Vehicle createVehicle(Vehicle vehicle) {
    return repository.save(vehicle);
  }

  @Transactional
  public Vehicle findVehicleById(final Long vehicleId) {
    return repository
        .findById(vehicleId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_VEHICLE_NOT_FOUND, vehicleId)));
  }

  @Transactional
  public Vehicle update(final Vehicle vehicle) {
    var entity =
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
  public List<Vehicle> findAllByVehicle() {
    return repository.findAll();
  }
}
