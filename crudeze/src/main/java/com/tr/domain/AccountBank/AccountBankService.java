package com.tr.domain.AccountBank;

import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountBankService {

  private static final String MSG_ACCOUNT_BANK_NOT_FOUND =
      "There is no account registration with the code %d";
  private static final String MSG_ACCOUNT_BANK_IN_USE =
      "Code account %d cannot be removed as it is in use";

  @Autowired
  private AccountBankRepository repository;

  @Transactional
  public AccountBankEntity createAccountBank(final AccountBankEntity accountBank) {
    return repository.save(accountBank);
  }

  @Transactional
  public AccountBankEntity findAccounById(final Long accountBankId) {
    return repository
        .findById(accountBankId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(MSG_ACCOUNT_BANK_NOT_FOUND, accountBankId)));
  }

  @Transactional
  public void deleteAccountBank(Long accountBankId) {
    try {
      repository.deleteById(accountBankId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_ACCOUNT_BANK_NOT_FOUND, accountBankId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_ACCOUNT_BANK_IN_USE, accountBankId));
    }
  }

  @Transactional
  public List<AccountBankEntity> findAllByAccountBanks() {
    return repository.findAll();
  }

  @Transactional
  public AccountBankEntity updateAccountBank(final AccountBankEntity accountBank) {
    AccountBankEntity entity =
        repository
            .findById(accountBank.getAccountBankId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setAccount(entity.getAccount());
    entity.setAgency(entity.getAgency());
    entity.setAgencyDV(entity.getAgencyDV());
    entity.setAccountDV(entity.getAccountDV());

    return repository.save(accountBank);
  }
}
