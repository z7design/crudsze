package com.tr.api.Departament;

import com.tr.domain.Departament.DepartamentEntity;
import com.tr.domain.Departament.DepartamentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/departament")
public class DepartamentController {
  @Autowired private DepartamentService service;

  @GetMapping
  public List<DepartamentEntity> findAll() {

    return service.findAllByDepartament();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DepartamentEntity createDepartament(@RequestBody DepartamentEntity departament) {
    return service.createDepartament(departament);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{departamentId}")
  public DepartamentEntity getDepartamentById(@PathVariable Long departamentId) {
    return service.findDepartamentById(departamentId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{departamentId}")
  public DepartamentEntity updateState(
      @PathVariable Long departamentId, @RequestBody DepartamentEntity departament) {
    DepartamentEntity departamentCurrent = service.findDepartamentById(departamentId);
    BeanUtils.copyProperties(departament, departamentCurrent, "departamentId");
    return service.updateDepartament(departamentCurrent);
  }

  @DeleteMapping("/{departamentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDepartament(@PathVariable Long departamentId) {
    service.deleteDepartament(departamentId);
  }
}
