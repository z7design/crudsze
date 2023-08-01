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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountPaybleService {

  private static final String MSG_ACCOUNT_PAYBLE_NOT_FOUND =
      "There is no account registration with the code %d";
  private static final String MSG_ACCOUNT_PAYBLE_IN_USE =
      "Code account %d cannot be removed as it is in use";

  @Autowired private AccountPaybleRepository repository;

  @Autowired private SupplierService supplierService;

  @Autowired private CategoryService categoryService;

  @Autowired private InstallmentService installmentService;

  @Transactional
  public AccountPayble createAccountPayble(final AccountPayble accountPayble) {
    Long supplierId = accountPayble.getSupplier().getSupplierId();
    Supplier supplier = supplierService.findSupplierById(supplierId);

    Long installmentId = accountPayble.getInstallment().getInstallmentId();
    Installment installment = installmentService.findInstallmentById(installmentId);

    accountPayble.setSupplier(supplier);
    accountPayble.setInstallment(installment);
    return repository.save(accountPayble);
  }

  @Transactional
  public AccountPayble findAccounPaybleById(final Long accountPaybleId) {
    return repository
        .findById(accountPaybleId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(MSG_ACCOUNT_PAYBLE_NOT_FOUND, accountPaybleId)));
  }

  @Transactional
  public void deleteAccountPaybleId(Long accountPaybleId) {
    try {
      repository.deleteById(accountPaybleId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(
          String.format(MSG_ACCOUNT_PAYBLE_NOT_FOUND, accountPaybleId));

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

    entity.setNumberDocument(entity.getNumberDocument());
    entity.setDateDocument(entity.getDateDocument());
    entity.setDueDate(entity.getDueDate());
    entity.setDescription(entity.getDescription());
    entity.setAmountPaid(entity.getAmountPaid());
    entity.setDiference(entity.getDiference());
    entity.setStatus(entity.getStatus());
    entity.setValueOfDocument(entity.getValueOfDocument());
    entity.setValueAccountPay(entity.getValueAccountPay());

    return repository.save(accountPayble);
  }
}
