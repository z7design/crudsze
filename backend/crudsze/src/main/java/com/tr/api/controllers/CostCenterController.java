package com.tr.api.controllers;

import com.tr.domain.entities.CostCenter;
import com.tr.domain.services.CostCenterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cost-center")
public class CostCenterController {

  @Autowired
  private CostCenterService service;

  @GetMapping
  public List<CostCenter> findAll() {
    return service.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CostCenter create(@RequestBody CostCenter costCenter) {
    return service.createCostCenter(costCenter);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{stateId}")
  public CostCenter update(@PathVariable Long costCenterId, @RequestBody CostCenter costCenter) {
    CostCenter costCenterCurrent = service.findById(costCenterId);
    BeanUtils.copyProperties(costCenter, costCenterCurrent, "costCenterId");
    return service.createCostCenter(costCenterCurrent);
  }

  @DeleteMapping("/{stateId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long costCenterId) {

    service.delete(costCenterId);
  }
}
