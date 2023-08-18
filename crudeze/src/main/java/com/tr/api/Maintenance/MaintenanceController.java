package com.tr.api.Maintenance;

import com.tr.domain.Maintenance.MaintenanceEntity;
import com.tr.domain.Maintenance.MaintenanceService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/maintenances")
public class MaintenanceController {

  @Autowired private MaintenanceService service;

  @GetMapping
  public List<MaintenanceEntity> findAllByMaintenance() {

    return service.findAllByMaintenance();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{maintenanceId}")
  public MaintenanceEntity getByMaintenanceId(@PathVariable Long maintenanceId) {
    return service.findMaintenanceById(maintenanceId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{maintenanceId}")
  public MaintenanceEntity update(
      @PathVariable Long maintenanceId, @RequestBody MaintenanceEntity maintenance) {
    MaintenanceEntity maintenanceCurrent = service.findMaintenanceById(maintenanceId);
    BeanUtils.copyProperties(maintenance, maintenanceCurrent, "maintenanceId");
    return service.createMaintenance(maintenanceCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MaintenanceEntity createMaintanance(@RequestBody MaintenanceEntity maintenance) {
    return service.createMaintenance(maintenance);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{maintenanceId}")
  public void deleteMaintenance(@PathVariable Long maintenanceId) {
    service.deleteMaintenance(maintenanceId);
  }
}
