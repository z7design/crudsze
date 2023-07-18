package com.tr.api.controllers;

import com.tr.domain.entities.Address;
import com.tr.domain.services.AddressService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {

  @Autowired private AddressService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Address> findAllByAddress() {
    return service.findAllByAddress();
  }

  @GetMapping("/{addressId}")
  @ResponseStatus(HttpStatus.OK)
  public Address getByAddressId(@PathVariable Long addressId) {
    return service.findAddressById(addressId);
  }

  @PutMapping("/{addressId}")
  @ResponseStatus(HttpStatus.OK)
  public Address updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
    Address addressCurrent = service.findAddressById(addressId);
    BeanUtils.copyProperties(address, addressCurrent, "addressId");
    return service.updateAddress(addressCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Address createAddress(@RequestBody Address address) {
    return service.createAddress(address);
  }

  @DeleteMapping("/{addressId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteByAddress(@PathVariable Long addressId) {
    service.deleteAddress(addressId);
  }
}
