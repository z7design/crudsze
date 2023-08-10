package com.tr.api.Check;

import com.tr.domain.Check.CheckBankEntity;
import com.tr.domain.Check.CheckService;
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
@RequestMapping("/checks")
public class CheckController {

  @Autowired private CheckService service;

  @GetMapping
  public List<CheckBankEntity> findAllByCity() {
    return service.findAllByCheck();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{checkId}")
  public CheckBankEntity getByCheckId(@PathVariable Long checkId) {
    return service.findCheckById(checkId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{checkId}")
  public CheckBankEntity update(@PathVariable Long checkId, @RequestBody CheckBankEntity check) {
    CheckBankEntity checkCurrent = service.findCheckById(checkId);
    BeanUtils.copyProperties(check, checkCurrent, "checkId");
    return service.createCheck(checkCurrent);
  }

  @PostMapping
  public ResponseEntity<CheckBankEntity> createCheck(@RequestBody CheckBankEntity check) {
    try {
      check = service.createCheck(check);
      return ResponseEntity.status(HttpStatus.CREATED).body(check);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{checkId}")
  public void deleteCheck(@PathVariable Long checkId) {
    service.deleteCheck(checkId);
  }
}
