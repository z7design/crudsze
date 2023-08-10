package com.tr.api.Phone;

import com.tr.domain.Phone.PhoneEntity;
import com.tr.domain.Phone.PhoneService;
import com.tr.domain.exception.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/phones")
public class PhoneController {
   @Autowired
   private PhoneService service;

  @GetMapping
  public List<PhoneEntity> findAllByPhone() {

    return service.findAllByPhone();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{phoneId}")
  public PhoneEntity getByPhoneId(@PathVariable Long phoneId) {
    
    return service.findPhoneById(phoneId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{phoneId}")
  public PhoneEntity update(@PathVariable Long phoneId, @RequestBody PhoneEntity phone) {
    PhoneEntity phoneCurrent = service.findPhoneById(phoneId);
    BeanUtils.copyProperties(phone, phoneCurrent, "phoneId");
    return service.createPhone(phoneCurrent);
  }

  @PostMapping
  public ResponseEntity<PhoneEntity> createPhone(@RequestBody PhoneEntity phone) {
    try {
      phone = service.createPhone(phone);
      return ResponseEntity.status(HttpStatus.CREATED).body(phone);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{phoneId}")
  public void deletePhone(@PathVariable Long phoneId) {

    service.deletePhone(phoneId);
  }
}
