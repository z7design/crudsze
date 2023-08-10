package com.tr.api.Employees;


import com.tr.domain.Employees.EmployeesEntity;
import com.tr.domain.Employees.EmployeesService;
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
  public List<EmployeesEntity> findAllByEmployees() {
    return service.findAllByEmployees();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{employeesId}")
  public EmployeesEntity getByEmployesId(@PathVariable Long employeesId) {
    return service.findEmployessById(employeesId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{employeesId}")
  public EmployeesEntity updateEmployees(
      @PathVariable Long employeesId, @RequestBody EmployeesEntity employees) {
    EmployeesEntity employeesCurrent = service.findEmployessById(employeesId);
    BeanUtils.copyProperties(employees, employeesCurrent, "employeesId");
    return service.createEmployees(employeesCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EmployeesEntity createEployess(@RequestBody EmployeesEntity employees) {
    return service.createEmployees(employees);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{employeesId}")
  public void deleteEmployess(@PathVariable Long employeesId) {

    service.deleteEmployess(employeesId);
  }
}
