package com.tr.api.Driver;

import com.tr.domain.Driver.DriverEntity;
import com.tr.domain.Driver.DriverService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/drivers")
public class DriverController {
  @Autowired private DriverService service;

  @GetMapping
  public List<DriverEntity> findAllByDriver() {

    return service.findAllByDrivers();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{driverId}")
  public DriverEntity getByDriverId(@PathVariable Long driverId) {
    return service.findDriverById(driverId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{driverId}")
  public DriverEntity update(@PathVariable Long driverId, @RequestBody DriverEntity driver) {
    DriverEntity driverCurrent = service.findDriverById(driverId);
    BeanUtils.copyProperties(driver, driverCurrent, "driverId");
    return service.createDriver(driverCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DriverEntity createDriver(@RequestBody DriverEntity driver) {
    return service.createDriver(driver);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{driverId}")
  public void deleteDriver(@PathVariable Long driverId) {

    service.deleteDriver(driverId);
  }
}
