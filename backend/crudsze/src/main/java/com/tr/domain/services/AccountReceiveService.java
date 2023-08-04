package com.tr.domain.services;

import com.tr.domain.entities.*;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.AccountReceiveRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountReceiveService {

  private static final String MSG_ACCOUNT_RECEIVE_NOT_FOUND =
      "There is no account registration with the code %d";
  private static final String MSG_ACCOUNT_RECEIVE_IN_USE =
      "Code account %d cannot be removed as it is in use";

  @Autowired private AccountReceiveRepository repository;
  @Autowired private ClientService clientService;
  @Autowired private CategoryService categoryService;
  @Autowired private InstallmentService installmentService;

  @Transactional
  public AccountReceive createAccountReceive(final AccountReceive accountReceive) {
    Long clientId = accountReceive.getClient().getClientId();
    Client client = clientService.findClientById(clientId);

    Long categoryId = accountReceive.getCategory().getCategoryId();
    Category category = categoryService.findCategoryById(categoryId);

    Long installmentId = accountReceive.getInstallment().getInstallmentId();
    Installment installment = installmentService.findInstallmentById(installmentId);

    accountReceive.setClient(client);
    accountReceive.setCategory(category);
    accountReceive.setInstallment(installment);
    return repository.save(accountReceive);
  }

  @Transactional
  public AccountReceive findAccounReceiveById(final Long accountReceiveId) {
    return repository
        .findById(accountReceiveId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(MSG_ACCOUNT_RECEIVE_NOT_FOUND, accountReceiveId)));
  }

  public void deleteAccountReceive(Long accountReceiveId) {
    try {
      repository.deleteById(accountReceiveId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(
          String.format(MSG_ACCOUNT_RECEIVE_NOT_FOUND, accountReceiveId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_ACCOUNT_RECEIVE_IN_USE, accountReceiveId));
    }
  }

  public List<AccountReceive> findAllByAccountReceive() {
    return repository.findAll();
  }

  @Transactional
  public AccountReceive updateAccountReceive(final AccountReceive accountReceive) {
    AccountReceive entity =
        repository
            .findById(accountReceive.getAccountReceiveId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setNumberDocument(entity.getNumberDocument());
    entity.setDateDocument(entity.getDateDocument());
    entity.setDueDate(entity.getDueDate());
    entity.setPayDate(entity.getPayDate());
    entity.setDescription(entity.getDescription());
    entity.setAmountPaid(entity.getAmountPaid());
    entity.setDiference(entity.getDiference());
    entity.setStatus(entity.getStatus());
    entity.setValueOfDocument(entity.getValueOfDocument());
    entity.setValueAccountReceivable(entity.getValueAccountReceivable());

    return repository.save(accountReceive);
  }
}
