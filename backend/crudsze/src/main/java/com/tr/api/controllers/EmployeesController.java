package com.tr.api.controllers;

import com.tr.domain.entities.Employees;
import com.tr.domain.services.EmployeesService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public Employees updateEmployees(
      @PathVariable Long employeesId, @RequestBody Employees employees) {
    Employees employeesCurrent = service.findEmployessById(employeesId);
    BeanUtils.copyProperties(employees, employeesCurrent, "employeesId");
    return service.createEmployees(employeesCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Employees createEployess(@RequestBody Employees employees) {
    return service.createEmployees(employees);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{employeesId}")
  public void deleteEmployess(@PathVariable Long employeesId) {

    service.deleteEmployess(employeesId);
  }
}
