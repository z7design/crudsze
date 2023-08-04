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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LedgerAccountService {

  private static final String MSG_LEAGER_ACCOUNT_NOT_FOUND =
      "There is no Ledger Account registration with the code %d";
  private static final String MSG_LEAGER_ACCOUNT_IN_USE =
      "Code Ledger Account %d cannot be removed as it is in use";

  @Autowired private LedgerAccountRepository repository;

  public LedgerAccount createLedgerAccount(LedgerAccount ledgerAccount) {
    return repository.save(ledgerAccount);
  }

  @Transactional
  public LedgerAccount findByLedgerAcountById(final Long ledgerAccountId) {
    return repository
        .findById(ledgerAccountId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(MSG_LEAGER_ACCOUNT_NOT_FOUND, ledgerAccountId)));
  }

  @Transactional
  public LedgerAccount updateLedgerAccount(final LedgerAccount ledgerAccount) {
    LedgerAccount entity =
        repository
            .findById(ledgerAccount.getLedgerAccountId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    entity.setDescription(entity.getDescription());
    entity.setCode(entity.getCode());

    return repository.save(ledgerAccount);
  }

  public void deleteLedgerAccount(Long ledgerAccountId) {
    try {
      repository.deleteById(ledgerAccountId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_LEAGER_ACCOUNT_IN_USE, ledgerAccountId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_LEAGER_ACCOUNT_IN_USE, ledgerAccountId));
    }
  }

  public List<LedgerAccount> findAlLedgerAccount() {
    return repository.findAll();
  }
}
