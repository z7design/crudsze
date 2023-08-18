package com.tr.api.Company;

import com.tr.domain.Company.CompanyEntity;
import com.tr.domain.Company.CompanyService;
import com.tr.domain.exception.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
  @Autowired private CompanyService service;

  @GetMapping
  public List<CompanyEntity> findAllByCompany() {

    return service.findAllByCompany();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{companyId}")
  public CompanyEntity getByCompanyId(@PathVariable Long companyId) {
    return service.findCompanyById(companyId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{companyId}")
  public CompanyEntity update(@PathVariable Long companyId, @RequestBody CompanyEntity company) {
    CompanyEntity companyCurrent = service.findCompanyById(companyId);
    BeanUtils.copyProperties(company, companyCurrent, "cityId");
    return service.createCompany(companyCurrent);
  }

  @PostMapping
  public ResponseEntity<CompanyEntity> createCompany(@RequestBody CompanyEntity company) {
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
