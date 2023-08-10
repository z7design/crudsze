package com.tr.api.Address;

import com.tr.domain.Address.AddressEntity;
import com.tr.domain.Address.AddressService;
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
  public List<AddressEntity> findAllByAddress() {
    return service.findAllByAddress();
  }

  @GetMapping("/{addressId}")
  @ResponseStatus(HttpStatus.OK)
  public AddressEntity getByAddressId(@PathVariable Long addressId) {
    return service.findAddressById(addressId);
  }

  @PutMapping("/{addressId}")
  @ResponseStatus(HttpStatus.OK)
  public AddressEntity updateAddress(@PathVariable Long addressId, @RequestBody AddressEntity address) {
    AddressEntity addressCurrent = service.findAddressById(addressId);
    BeanUtils.copyProperties(address, addressCurrent, "addressId");
    return service.updateAddress(addressCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AddressEntity createAddress(@RequestBody AddressEntity address) {
    return service.createAddress(address);
  }

  @DeleteMapping("/{addressId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteByAddress(@PathVariable Long addressId) {
    service.deleteAddress(addressId);
  }
}
