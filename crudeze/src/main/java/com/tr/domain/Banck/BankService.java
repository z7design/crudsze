package com.tr.domain.Banck;

import com.tr.domain.AccountBank.AccountBankEntity;
import com.tr.domain.AccountBank.AccountBankService;
import com.tr.domain.Address.AddressEntity;
import com.tr.domain.Address.AddressService;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

  private static final String MSG_BANK_NOT_FOUND = "There is no bank registration with the code %d";
  private static final String MSG_BANK_IN_USE = "Code bank %d cannot be removed as it is in use";

  @Autowired 
  private BankRepository repository;

  @Autowired 
  private AddressService addressService;

  @Autowired 
  private AccountBankService accountBankService;

  @Transactional
  public BankEntity createBank(final BankEntity bankEntity) {
    Long addressId = bankEntity.getAddress().getAddressId();
    AddressEntity address = addressService.findAddressById(addressId);

    Long accountBankId = bankEntity.getAccountBank().getAccountBankId();
    AccountBankEntity accountBank = accountBankService.findAccounById(accountBankId);

    bankEntity.setAddress(address);
    bankEntity.setAccountBank(accountBank);
    return repository.save(bankEntity);
  }

  @Transactional
  public BankEntity findBankById(final Long bankId) {
    return repository
        .findById(bankId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_BANK_NOT_FOUND, bankId)));
  }
  
  public void deleteBank(Long bankId) {
    try {
      repository.deleteById(bankId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_BANK_NOT_FOUND, bankId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_BANK_IN_USE, bankId));
    }
  }

  public List<BankEntity> findAllByBanks() {
    return repository.findAll();
  }
}
