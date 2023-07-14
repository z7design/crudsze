package com.tr.api.controllers;

import com.tr.domain.entities.Company;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.CompanyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
  @Autowired private CompanyService service;

  @GetMapping
  public List<Company> findAllByCompany() {

    return service.findAllByCompany();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{companyId}")
  public Company getByCompanyId(@PathVariable Long companyId) {
    return service.findCompanyById(companyId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{companyId}")
  public Company update(@PathVariable Long companyId, @RequestBody Company company) {
    Company companyCurrent = service.findCompanyById(companyId);
    BeanUtils.copyProperties(company, companyCurrent, "cityId");
    return service.createCompany(companyCurrent);
  }

  @PostMapping
  public ResponseEntity<Company> createCompany(@RequestBody Company company) {
    try {
      company = service.createCompany(company);
      return ResponseEntity.status(HttpStatus.CREATED).body(company);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{companyId}")
  public void deleteCompany(@PathVariable Long companyId) {

    service.deleteCompany(companyId);
  }
}
