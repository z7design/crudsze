package com.tr.api.controllers;

import com.tr.domain.entities.Vehicle;
import com.tr.domain.services.VehicleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {

  @Autowired private VehicleService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Vehicle> findAllByVehicle() {
    return service.findAllByVehicle();
  }

  @GetMapping("/{vehicleId}")
  @ResponseStatus(HttpStatus.OK)
  public Vehicle getByVehicleId(@PathVariable Long vehicleId) {
    return service.findVehicleById(vehicleId);
  }

  @PutMapping("/{vehicleId}")
  @ResponseStatus(HttpStatus.OK)
  public Vehicle updateVeicle(@PathVariable Long vehicleId, @RequestBody Vehicle vehicle) {
    Vehicle vehicleCurrent = service.findVehicleById(vehicleId);
    BeanUtils.copyProperties(vehicle, vehicleCurrent, "vehicleId");
    return service.update(vehicleCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
    return service.createVehicle(vehicle);
  }

  @DeleteMapping("/{vehicleId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteByVehicle(@PathVariable Long vehicleId) {
    service.deleteVehicle(vehicleId);
  }
}
