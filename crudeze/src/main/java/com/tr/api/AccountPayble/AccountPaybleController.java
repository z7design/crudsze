package com.tr.api.AccountPayble;

import com.tr.domain.AccountPayble.AccountPaybleEntity;
import com.tr.domain.AccountPayble.AccountPaybleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/account-payble")
public class AccountPaybleController {

  @Autowired private AccountPaybleService service;

  @GetMapping
  public List<AccountPaybleEntity> findAllByAccountPayble() {
    return service.findAllByAccountPayble();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{accountPaybleId}")
  public AccountPaybleEntity getByAccountPaylbleId(@PathVariable Long accountPaybleId) {
    return service.findAccounPaybleById(accountPaybleId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{accountPaybleId}")
  public AccountPaybleEntity updateAccountPayble(
      @PathVariable Long accountPaybleId, @RequestBody AccountPaybleEntity accountPayble) {
    AccountPaybleEntity accountPaybleCurrent = service.findAccounPaybleById(accountPaybleId);
    BeanUtils.copyProperties(accountPayble, accountPaybleCurrent, "accountPaybleId");
    return service.updateAccountPayble(accountPaybleCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AccountPaybleEntity createAccountPaybleEntiy(@RequestBody AccountPaybleEntity accountPaybleEntiy) {
    return service.createAccountPayble(accountPaybleEntiy);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{accountPaybleId}")
  public void deleteAccountPayble(@PathVariable Long accountPaybleId) {
    service.deleteAccountPaybleId(accountPaybleId);
  }
}
