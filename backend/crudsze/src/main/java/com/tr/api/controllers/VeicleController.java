package com.tr.api.controllers;

import com.tr.domain.entities.Veicle;
import com.tr.domain.services.VeicleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/veicles")
public class VeicleController {

  @Autowired private VeicleService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Veicle> findAllByAddress() {
    return service.findAllByVeicle();
  }

  @GetMapping("/{veicleId}")
  @ResponseStatus(HttpStatus.OK)
  public Veicle getByVeicleId(@PathVariable Long veicleId) {
    return service.findVeicleById(veicleId);
  }

  @PutMapping("/{veicleId}")
  @ResponseStatus(HttpStatus.OK)
  public Veicle updateVeicle(@PathVariable Long veicleId, @RequestBody Veicle veicle) {
    Veicle veicleCurrent = service.findVeicleById(veicleId);
    BeanUtils.copyProperties(veicle, veicleCurrent, "veicleId");
    return service.update(veicleCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Veicle createVeicle(@RequestBody Veicle veicle) {
    return service.createVeicle(veicle);
  }

  @DeleteMapping("/{veicleId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteByVeicle(@PathVariable Long veicleId) {
    service.deleteVeicle(veicleId);
  }
}
