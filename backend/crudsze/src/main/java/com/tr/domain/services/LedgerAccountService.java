package com.tr.domain.services;

import com.tr.domain.entities.LedgerAccount;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.LedgerAccountRepository;
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
public class LedgerAccountService {

  private static final String MSG_LEAGER_ACCOUNT_NOT_FOUND = "There is no Ledger Account registration with the code %d";
  private static final String MSG_LEAGER_ACCOUNT_IN_USE = "Code Ledger Account %d cannot be removed as it is in use";

  @Autowired
  private LedgerAccountRepository repository;
   public LedgerAccount createLedgerAccount(LedgerAccount ledgerAccount) {
    return repository.save(ledgerAccount);
  }


  @Transactional
  public LedgerAccount findByIdLedgerAcount(final Long ledgerAccountId) {
    return repository
        .findById(ledgerAccountId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_LEAGER_ACCOUNT_NOT_FOUND, ledgerAccountId)));
  }

  @Transactional
  public LedgerAccount update(final LedgerAccount ledgerAccount) {
    var entity =
        repository
            .findById(ledgerAccount.getLedgerAccountId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));


    return repository.save(ledgerAccount);
  }

  @DeleteMapping("/{ledgerAccountId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteLedgerAccount(Long ledgerAccountId) {
    try {
      repository.deleteById(ledgerAccountId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_LEAGER_ACCOUNT_IN_USE, ledgerAccountId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_LEAGER_ACCOUNT_IN_USE, ledgerAccountId));
    }
  }

  public List<LedgerAccount> findAlLedgerAccountl() {
    return repository.findAll();
  }
}
