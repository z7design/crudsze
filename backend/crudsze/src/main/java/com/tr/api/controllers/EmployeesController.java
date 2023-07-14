package com.tr.api.controllers;

import com.tr.domain.entities.Employees;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.EmployeesService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {

  @Autowired private EmployeesService service;

  @GetMapping
  public List<Employees> findAllByEmployees() {
    return service.findAllByEmployees();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{employeesId}")
  public Employees getByEmployesId(@PathVariable Long employeesId) {
    return service.findEmployessById(employeesId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{employeesId}")
  public Employees update(@PathVariable Long employeesId, @RequestBody Employees employees) {
    Employees employeesCurrent = service.findEmployessById(employeesId);
    BeanUtils.copyProperties(employees, employeesCurrent, "employeesId");
    return service.createEmployees(employeesCurrent);
  }

  @PostMapping
  public ResponseEntity<Employees> createEployess(@RequestBody Employees employees) {
    try {
      employees = service.createEmployees(employees);
      return ResponseEntity.status(HttpStatus.CREATED).body(employees);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{employeesId}")
  public void deleteEmployess(@PathVariable Long employeesId) {

    service.deleteEmployess(employeesId);
  }
}
