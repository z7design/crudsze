package com.tr.api.controllers;

import com.tr.domain.entities.Maintenance;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.MaintenanceService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/maintenances")
public class MaintenanceController {

  @Autowired private MaintenanceService service;

  @GetMapping
  public List<Maintenance> findAllByMaintenance() {
    return service.findAllByMaintenance();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{maintenanceId}")
  public Maintenance getByMaintenanceId(@PathVariable Long maintenanceId) {
    return service.findMaintenanceById(maintenanceId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{maintenanceId}")
  public Maintenance update(@PathVariable Long maintenanceId, @RequestBody Maintenance maintenance) {
    Maintenance maintenanceCurrent = service.findMaintenanceById(maintenanceId);
    BeanUtils.copyProperties(maintenance, maintenanceCurrent, "maintenanceId");
    return service.createMaintenance(maintenanceCurrent);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping
  public ResponseEntity<Maintenance> createMaintenance(@RequestBody Maintenance maintenance) {
    try {
      maintenance = service.createMaintenance(maintenance);
      return ResponseEntity.status(HttpStatus.CREATED).body(maintenance);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{maintenanceId}")
  public void deleteCity(@PathVariable Long maintenanceId) {
    service.deleteMaintenance(maintenanceId);
  }
}
