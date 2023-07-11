package com.tr.api.controllers;

import com.tr.domain.entities.Address;
import com.tr.domain.services.AddressService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {
    
  private AddressService service;
      
  @GetMapping
  public List<Address> findAllByAddress() {

    return service.findAllByAddress();
  }

  @GetMapping("/{addressId}")
  public Address getByAddressId(@PathVariable Long addressId) {
    return service.findAddressById(addressId);
  }

  @PutMapping("/{addressId}")
  public Address update(@PathVariable Long addressId, @RequestBody Address address) {
    Address addressCurrent = service.findAddressById(addressId);
    BeanUtils.copyProperties(address, addressCurrent, "addressId");
    return service.createAddress(addressCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Address createAddress(@RequestBody Address address) {
    return service.createAddress(address);
  }

  @DeleteMapping("/{categoryId}")
  public void deleteCategory(@PathVariable Long addressId) {
    
    service.deleteAddress(addressId);
  }
}
