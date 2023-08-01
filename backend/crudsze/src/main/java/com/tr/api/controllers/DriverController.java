package com.tr.api.controllers;

import com.tr.domain.entities.Driver;
import com.tr.domain.services.DriverService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {
  @Autowired private DriverService service;

  @GetMapping
  public List<Driver> findAllByDriver() {

    return service.findAllByDrivers();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{driverId}")
  public Driver getByDriverId(@PathVariable Long driverId) {
    return service.findDriverById(driverId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{driverId}")
  public Driver update(@PathVariable Long driverId, @RequestBody Driver driver) {
    Driver driverCurrent = service.findDriverById(driverId);
    BeanUtils.copyProperties(driver, driverCurrent, "driverId");
    return service.createDriver(driverCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Driver createDriver(@RequestBody Driver driver) {
    return service.createDriver(driver);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{driverId}")
  public void deleteDriver(@PathVariable Long driverId) {

    service.deleteDriver(driverId);
  }
}
