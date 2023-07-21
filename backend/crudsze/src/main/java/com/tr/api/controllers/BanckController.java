package com.tr.api.controllers;

import com.tr.domain.entities.Bank;
import com.tr.domain.services.BankService;
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
  public List<Bank> findAllByCity() {

    return service.findAllByCities();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{bankId}")
  public Bank getByBankId(@PathVariable Long bankId) {
    return service.findBankById(bankId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{bankId}")
  public Bank update(@PathVariable Long bankId, @RequestBody Bank bank) {
    Bank bankCurrent = service.findBankById(bankId);
    BeanUtils.copyProperties(bank, bankCurrent, "bankId");
    return service.createBank(bankCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Bank createBank(@RequestBody Bank bank) {

    return service.createBank(bank);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{bankId}")
  public void deleteBank(@PathVariable Long bankId) {

    service.deleteBank(bankId);
  }
}
