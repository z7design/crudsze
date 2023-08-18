package com.tr.api.Supplier;

import com.tr.domain.Supplier.SupplierEntity;
import com.tr.domain.Supplier.SupplierService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/suppliers")
public class SupplierController {

  @Autowired
  private SupplierService service;

  @GetMapping
  public List<SupplierEntity> findAllBySupplier() {

    return service.findAllBySupplier();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{supplierId}")
  public SupplierEntity getBySupplierId(@PathVariable Long supplierId) {
    return service.findSupplierById(supplierId);
  }

  @PutMapping("/{supplierId}")
  @ResponseStatus(HttpStatus.OK)
  public SupplierEntity updateSupplier(@PathVariable Long supplierId, @RequestBody SupplierEntity supplier) {
    SupplierEntity supplierCurrent = service.findSupplierById(supplierId);
    BeanUtils.copyProperties(supplier, supplierCurrent, "supplierId");
    return service.updateSupplier(supplierCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SupplierEntity createSupplier(@RequestBody SupplierEntity supplier) {
    return service.createSupplier(supplier);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{supplierId}")
  public void deleteCity(@PathVariable Long supplierId) {

    service.deleteSupplier(supplierId);
  }
}

