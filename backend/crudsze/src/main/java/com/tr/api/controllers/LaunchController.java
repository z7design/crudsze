package com.tr.api.controllers;

import com.tr.domain.entities.Launch;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.LaunchService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/launch")
public class LaunchController {
  
  @Autowired
  private LaunchService service;

  @GetMapping
  public List<Launch> findAllByCity() {

    return service.findAllByLaunch();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{launchId}")
  public Launch getByLaunch(@PathVariable Long launchId) {
    return service.findLaunchById(launchId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{launchId}")
  public Launch update(@PathVariable Long launchId, @RequestBody Launch launch) {
    Launch launchCurrent = service.findLaunchById(launchId);
    BeanUtils.copyProperties(launch, launchCurrent, "launchId");
    return service.createLaunch(launchCurrent);
  }

  @PostMapping
  public ResponseEntity<Launch> createLaunch(@RequestBody Launch launch) {
    try {
      launch = service.createLaunch(launch);
      return ResponseEntity.status(HttpStatus.CREATED).body(launch);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{launchId}")
  public void deleteLaunch(@PathVariable Long launchId) {

    service.deleteLaunch(launchId);
  }
}
