package com.tr.api.controllers;

import com.tr.domain.entities.AccountPayble;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.AccountPaybleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/account-payble")
public class AccountPaybleController {

  @Autowired private AccountPaybleService service;

  @GetMapping
  public List<AccountPayble> findAllByAccountPayble() {
    return service.findAllByAccountPayble();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{accountPaybleId}")
  public AccountPayble getByAccountPaylbleId(@PathVariable Long accountPaybleId) {
    return service.findAccounPaybleById(accountPaybleId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{accountPaybleId}")
  public AccountPayble updateAccountPayble(
      @PathVariable Long accountPaybleId, @RequestBody AccountPayble accountPayble) {
    AccountPayble accountPaybleCurrent = service.findAccounPaybleById(accountPaybleId);
    BeanUtils.copyProperties(accountPayble, accountPaybleCurrent, "accountPaybleId");
    return service.createAccountPayble(accountPaybleCurrent);
  }

  @PostMapping
  public ResponseEntity<AccountPayble> createAccountPayble(
      @RequestBody AccountPayble accountPayble) {
    try {
      accountPayble = service.createAccountPayble(accountPayble);
      return ResponseEntity.status(HttpStatus.CREATED).body(accountPayble);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{cityId}")
  public void deleteAccountBank(@PathVariable Long accountPaybleId) {
    service.deleteAccountPaybleId(accountPaybleId);
  }
}
