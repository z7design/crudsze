package com.tr.api.controllers;

import com.tr.domain.entities.Launch;
import com.tr.domain.services.LaunchService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/launch")
public class LaunchController {

  @Autowired private LaunchService service;

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
  @ResponseStatus(HttpStatus.CREATED)
  public Launch createLaunch(@RequestBody Launch launch) {
    return service.createLaunch(launch);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{launchId}")
  public void deleteLaunch(@PathVariable Long launchId) {
    service.deleteLaunch(launchId);
  }
}
