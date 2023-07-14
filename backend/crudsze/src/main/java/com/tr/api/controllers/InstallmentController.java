package com.tr.api.controllers;

import com.tr.domain.entities.Installment;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.InstallmentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/installment")
public class InstallmentController {

  @Autowired private InstallmentService service;

  @GetMapping
  public List<Installment> findAllByInstallment() {
    return service.findAllByInstallment();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{InstallmentId}")
  public Installment getByInstallmentyId(@PathVariable Long installmenId) {
    return service.findInstallmentById(installmenId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{InstallmentId}")
  public Installment updateInstalment(@PathVariable Long installmenId, @RequestBody Installment installment) {
    Installment insttallmentCurrent = service.findInstallmentById(installmenId);
    BeanUtils.copyProperties(installment, insttallmentCurrent, "InstallmentId");
    return service.createInstallment(insttallmentCurrent);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping
  public ResponseEntity<Installment> createInstallment(@RequestBody Installment installment) {
    try {
      installment = service.createInstallment(installment);
      return ResponseEntity.status(HttpStatus.CREATED).body(installment);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{installmenId}")
  public void deleteCity(@PathVariable Long installmenId) {
    service.deleteInstallment(installmenId);
  }
}
