package com.tr.api.controllers;

import com.tr.domain.entities.Departament;
import com.tr.domain.services.DepartamentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departament")
public class DepartamentController {
  @Autowired private DepartamentService service;

  @GetMapping
  public List<Departament> findAll() {

    return service.findAllByDepartament();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Departament createDepartament(@RequestBody Departament departament) {
    return service.createDepartament(departament);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{departamentId}")
  public Departament getDepartamentById(@PathVariable Long departamentId) {
    return service.findDepartamentById(departamentId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{departamentId}")
  public Departament updateState(
      @PathVariable Long departamentId, @RequestBody Departament departament) {
    Departament departamentCurrent = service.findDepartamentById(departamentId);
    BeanUtils.copyProperties(departament, departamentCurrent, "departamentId");
    return service.updateDepartament(departamentCurrent);
  }

  @DeleteMapping("/{departamentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDepartament(@PathVariable Long departamentId) {
    service.deleteDepartament(departamentId);
  }
}
