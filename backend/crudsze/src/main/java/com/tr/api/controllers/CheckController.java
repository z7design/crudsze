package com.tr.api.controllers;

import com.tr.domain.entities.CheckBank;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.CheckService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/checks")
public class CheckController {

  @Autowired private CheckService service;

  @GetMapping
  public List<CheckBank> findAllByCity() {
    return service.findAllByCheck();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{checkId}")
  public CheckBank getByCheckId(@PathVariable Long checkId) {
    return service.findCheckById(checkId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{checkId}")
  public CheckBank update(@PathVariable Long checkId, @RequestBody CheckBank check) {
    CheckBank checkCurrent = service.findCheckById(checkId);
    BeanUtils.copyProperties(check, checkCurrent, "checkId");
    return service.createCheck(checkCurrent);
  }

  @PostMapping
  public ResponseEntity<CheckBank> createCheck(@RequestBody CheckBank check) {
    try {
      check = service.createCheck(check);
      return ResponseEntity.status(HttpStatus.CREATED).body(check);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{checkId}")
  public void deleteCheck(@PathVariable Long checkId) {
    service.deleteCheck(checkId);
  }
}
