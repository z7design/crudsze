package com.tr.api.AccountBank;

import com.tr.domain.AccountBank.AccountBankEntity;
import com.tr.domain.AccountBank.AccountBankService;
import com.tr.domain.exception.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account-bank")
public class AccountBankController {

  @Autowired private AccountBankService service;

  @GetMapping
  public List<AccountBankEntity> findAllByAccountBank() {

    return service.findAllByAccountBanks();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{accountBankId}")
  public AccountBankEntity getByAccountBankId(@PathVariable Long accountBankId) {
    return service.findAccounById(accountBankId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{accountBankId}")
  public AccountBankEntity updateAccountBank(
      @PathVariable Long accountBankId, @RequestBody AccountBankEntity accountBank) {
    AccountBankEntity accountBankCurrent = service.findAccounById(accountBankId);
    BeanUtils.copyProperties(accountBank, accountBankCurrent, "accountBankId");
    return service.createAccountBank(accountBankCurrent);
  }

  @PostMapping
  public ResponseEntity<AccountBankEntity> createAccountBank(
      @RequestBody AccountBankEntity accountBank) {
    try {
      accountBank = service.createAccountBank(accountBank);
      return ResponseEntity.status(HttpStatus.CREATED).body(accountBank);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{accountBankId}")
  public void deleteAccountBank(@PathVariable Long accountBankId) {

    service.deleteAccountBank(accountBankId);
  }
}
