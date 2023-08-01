package com.tr.api.controllers;

import com.tr.domain.entities.LedgerAccount;
import com.tr.domain.services.LedgerAccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/ledger-account")
public class LedgerAccountController {

  @Autowired private LedgerAccountService service;

  @GetMapping
  public List<LedgerAccount> findAllByLedgerAccount() {
    return service.findAlLedgerAccount();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{ledgerAccountId}")
  public LedgerAccount getByLedgerAccountId(@PathVariable Long ledgerAccountId) {
    return service.findByLedgerAcountById(ledgerAccountId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{ledgerAccountId}")
  public LedgerAccount update(@PathVariable Long ledgerAccountId, @RequestBody LedgerAccount ledgerAccount) {
    LedgerAccount ledgerAccountCurrent = service.findByLedgerAcountById(ledgerAccountId);
    BeanUtils.copyProperties(ledgerAccount, ledgerAccountCurrent, "ledgerAccountId");
    return service.updateLedgerAccount(ledgerAccountCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LedgerAccount createLedgerAccount(@RequestBody LedgerAccount ledgerAccount) {
    return service.createLedgerAccount(ledgerAccount);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{ledgerAccountId}")
  public void deleteLedgerAccount(@PathVariable Long ledgerAccountId) {
    service.deleteLedgerAccount(ledgerAccountId);
  }
}
