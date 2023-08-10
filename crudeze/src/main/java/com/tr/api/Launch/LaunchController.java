package com.tr.api.Launch;

import com.tr.domain.Launch.LaunchEntity;
import com.tr.domain.Launch.LaunchService;
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
  public List<LaunchEntity> findAllByLaunch() {
    return service.findAllByLaunchEntity();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{launchId}")
  public LaunchEntity getByLaunchById(@PathVariable Long launchId) {
    return service.findLaunchEntityById(launchId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{launchId}")
  public LaunchEntity update(@PathVariable Long launchId, @RequestBody LaunchEntity launchEntity) {
    LaunchEntity launchCurrent = service.findLaunchEntityById(launchId);
    BeanUtils.copyProperties(launchEntity, launchCurrent, "launchId");
    return service.createLaunch(launchCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LaunchEntity createLaunch(@RequestBody LaunchEntity launchEntity) {
    return service.createLaunch(launchEntity);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{launchId}")
  public void deleteLaunch(@PathVariable Long launchId) {
    service.deleteLaunch(launchId);
  }
}
