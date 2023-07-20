package com.tr.domain.services;

import com.tr.domain.entities.Maintenance;
import com.tr.domain.entities.Veicle;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.MaintenanceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceService {

  private static final String MSG_MAINTENACE_NOT_FOUND = "There is no Maintenance registration with the code %d";
  private static final String MSG_MAINTENACE_IN_USE = "Code Maintenance %d cannot be removed as it is in use";

  @Autowired private MaintenanceRepository repository;
  @Autowired private VeicleService veicleService;

  @Transactional
  public Maintenance createMaintenance(final Maintenance maintenance) {
    Long maintenaceId = maintenance.getVeicle().getVeicleId();
    Veicle veicle = veicleService.findVeicleById(maintenaceId);

    maintenance.setVeicle(veicle);
    return repository.save(maintenance);
  }

  @Transactional
  public Maintenance findMaintenanceById(final Long maintenanceId) {
    return repository
        .findById(maintenanceId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_MAINTENACE_NOT_FOUND, maintenanceId)));
  }

  @Transactional
  public Maintenance updateMaintenance(final Maintenance maintenance) {
    var entity =
        repository
            .findById(maintenance.getManutencaoId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));
    
    entity.setKm(entity.getKm());
    entity.setDescription(entity.getDescription());
    entity.setDateInitial(entity.getDateInitial());
    entity.setDateFinish(entity.getDateFinish());
    entity.setVeicle(entity.getVeicle());
    entity.setTypeMaintenance(entity.getTypeMaintenance());
    return repository.save(maintenance);
  }
  
  public void deleteMaintenance(Long maintenanceId) {
    try {
      repository.deleteById(maintenanceId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_MAINTENACE_NOT_FOUND, maintenanceId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_MAINTENACE_IN_USE, maintenanceId));
    }
  }

  public List<Maintenance> findAllByMaintenance() {
    return repository.findAll();
  }
}
