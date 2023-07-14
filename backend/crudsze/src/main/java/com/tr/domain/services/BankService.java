package com.tr.domain.services;

import com.tr.domain.entities.Address;
import com.tr.domain.entities.Bank;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.repositories.BankRepository;
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
public class BankService {

  private static final String MSG_BANK_NOT_FOUND = "There is no bank registration with the code %d";
  private static final String MSG_BANK_IN_USE = "Code bank %d cannot be removed as it is in use";

  @Autowired private BankRepository repository;

  @Autowired private AddressService addressService;

  @Transactional
  public Bank createBank(final Bank bank) {
    Long addressId = bank.getAddress().getAddressId();
    Address address = addressService.findAddressById(addressId);

    bank.setAddress(address);
    return repository.save(bank);
  }

  @Transactional
  public Bank findBankById(final Long bankId) {
    return repository
        .findById(bankId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_BANK_NOT_FOUND, bankId)));
  }

  @DeleteMapping("/{bankId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBank(Long bankId) {
    try {
      repository.deleteById(bankId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_BANK_NOT_FOUND, bankId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_BANK_IN_USE, bankId));
    }
  }

  public List<Bank> findAllByCities() {

    return repository.findAll();
  }
}
