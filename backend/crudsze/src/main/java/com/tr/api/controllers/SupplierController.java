package com.tr.api.controllers;

import com.tr.domain.entities.Supplier;
import com.tr.domain.services.SupplierService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/suppliers")
public class SupplierController {

  @Autowired
  private SupplierService service;

  @GetMapping
  public List<Supplier> findAllBySupplier() {

    return service.findAllBySupplier();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{supplierId}")
  public Supplier getBySupplierId(@PathVariable Long supplierId) {
    return service.findSupplierById(supplierId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{supplierId}")
  public Supplier updateSupplier(@PathVariable Long supplierId, @RequestBody Supplier supplier) {
    Supplier supplierCurrent = service.findSupplierById(supplierId);
    BeanUtils.copyProperties(supplierCurrent, supplierCurrent, "supplierId");
    return service.updateSupplier(supplierCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Supplier createSupplier(@RequestBody Supplier supplier) {
    return service.createSupplier(supplier);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{supplierId}")
  public void deleteCity(@PathVariable Long supplierId) {

    service.deleteSupplier(supplierId);
  }
}

