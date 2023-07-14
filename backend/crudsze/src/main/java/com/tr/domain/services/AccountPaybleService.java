package com.tr.domain.services;

import com.tr.domain.entities.*;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.AccountPaybleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
public class AccountPaybleService {
  
  private static final String MSG_ACCOUNT_PAYBLE_NOT_FOUND = "There is no account registration with the code %d";
  private static final String MSG_ACCOUNT_PAYBLE_IN_USE = "Code account %d cannot be removed as it is in use";

  @Autowired
  private AccountPaybleRepository repository;

  @Transactional
  public AccountPayble createAccountPayble(final AccountPayble accountPayble) {
    Long addressId = accountBank.getAddress().getAddressId();
    Address address = addressService.findAddressById(addressId);

    accountBank.setAddress(address);
    return repository.save(accountBank);
  }

  @Transactional
  public AccountPayble findAccounPaybleById(final Long accountPaybleId) {
    return repository
        .findById(accountPaybleId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_ACCOUNT_PAYBLE_NOT_FOUND, accountPaybleId)));
  }

  @DeleteMapping("/{accountPaybleId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAccountBank(Long accountPaybleId) {
    try {
      repository.deleteById(accountPaybleId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_ACCOUNT_PAYBLE_NOT_FOUND, accountPaybleId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_ACCOUNT_PAYBLE_IN_USE, accountPaybleId));
    }
  }

  public List<AccountPayble> findAllByAccountPayble() {

    return repository.findAll();
  }
  
    @Transactional
  public AccountPayble updateAccountPayble(final AccountPayble accountPayble) {
    var entity =
        repository
            .findById(accountPayble.getAccountPaybleId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setAccount(entity.getAccount());


    return repository.save(accountPayble);
  }

}
