package com.tr.api.Banck;

import com.tr.domain.Banck.BankEntity;
import com.tr.domain.Banck.BankService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/banks")
public class BanckController {
  @Autowired private BankService service;

  @GetMapping
  public List<BankEntity> findAllByCity() {

    return service.findAllByBanks();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{bankId}")
  public BankEntity getByBankId(@PathVariable Long bankId) {
    return service.findBankById(bankId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{bankId}")
  public BankEntity update(@PathVariable Long bankId, @RequestBody BankEntity bank) {
    BankEntity bankCurrent = service.findBankById(bankId);
    BeanUtils.copyProperties(bank, bankCurrent, "bankId");
    return service.createBank(bankCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BankEntity createBank(@RequestBody BankEntity bank) {

    return service.createBank(bank);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{bankId}")
  public void deleteBank(@PathVariable Long bankId) {

    service.deleteBank(bankId);
  }
}
