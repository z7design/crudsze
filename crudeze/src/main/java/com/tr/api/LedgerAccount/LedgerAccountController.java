package com.tr.api.LedgerAccount;

import com.tr.domain.LedgerAccount.LedgerAccountEntity;
import com.tr.domain.LedgerAccount.LedgerAccountService;
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
  public List<LedgerAccountEntity> findAllByLedgerAccount() {
    return service.findAlLedgerAccount();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{ledgerAccountId}")
  public LedgerAccountEntity getByLedgerAccountId(@PathVariable Long ledgerAccountId) {
    return service.findByLedgerAcountById(ledgerAccountId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{ledgerAccountId}")
  public LedgerAccountEntity update(@PathVariable Long ledgerAccountId, @RequestBody LedgerAccountEntity ledgerAccount) {
    LedgerAccountEntity ledgerAccountCurrent = service.findByLedgerAcountById(ledgerAccountId);
    BeanUtils.copyProperties(ledgerAccount, ledgerAccountCurrent, "ledgerAccountId");
    return service.updateLedgerAccount(ledgerAccountCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LedgerAccountEntity createLedgerAccount(@RequestBody LedgerAccountEntity ledgerAccount) {
    return service.createLedgerAccount(ledgerAccount);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{ledgerAccountId}")
  public void deleteLedgerAccount(@PathVariable Long ledgerAccountId) {
    service.deleteLedgerAccount(ledgerAccountId);
  }

}
