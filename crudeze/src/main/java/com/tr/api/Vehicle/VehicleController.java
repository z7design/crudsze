package com.tr.api.Vehicle;

import com.tr.domain.Vehicle.VehicleEntity;
import com.tr.domain.Vehicle.VehicleService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

  @Autowired private VehicleService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<VehicleEntity> findAllByVehicle() {
    return service.findAllByVehicle();
  }

  @GetMapping("/{vehicleId}")
  @ResponseStatus(HttpStatus.OK)
  public VehicleEntity getByVehicleId(@PathVariable Long vehicleId) {
    return service.findVehicleById(vehicleId);
  }

  @PutMapping("/{vehicleId}")
  @ResponseStatus(HttpStatus.OK)
  public VehicleEntity updateVeicle(@PathVariable Long vehicleId, @RequestBody VehicleEntity vehicle) {
    VehicleEntity vehicleCurrent = service.findVehicleById(vehicleId);
    BeanUtils.copyProperties(vehicle, vehicleCurrent, "vehicleId");
    return service.update(vehicleCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VehicleEntity createVehicle(@RequestBody VehicleEntity vehicle) {
    return service.createVehicle(vehicle);
  }

  @DeleteMapping("/{vehicleId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteByVehicle(@PathVariable Long vehicleId) {
    service.deleteVehicle(vehicleId);
  }
}
