package com.tr.api.controllers;

import com.tr.domain.entities.AccountReceive;
import com.tr.domain.services.AccountReceiveService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/account-receive")
public class AccountReceiveController {
  
  @Autowired private AccountReceiveService service;

  @GetMapping
  public List<AccountReceive> findAllByAccountReceive() {
    return service.findAllByAccountReceive();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{accountReceiveId}")
  public AccountReceive getByAccountBankId(@PathVariable Long accountReceiveId) {
    return service.findAccounReceiveById(accountReceiveId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{accountReceiveId}")
  public AccountReceive updateAccountReceive(
      @PathVariable Long accountReceiveId, @RequestBody AccountReceive accountReceive) {
    AccountReceive accountReceiveCurrent = service.findAccounReceiveById(accountReceiveId);
    BeanUtils.copyProperties(accountReceive, accountReceiveCurrent, "accountBankId");
    return service.updateAccountReceive(accountReceiveCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AccountReceive createAccountReceive(@RequestBody AccountReceive accountReceive) {
    return service.createAccountReceive(accountReceive);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{cityId}")
  public void deleteAccountReceive(@PathVariable Long accountReceiveId) {

    service.deleteAccountReceive(accountReceiveId);
  }
}
