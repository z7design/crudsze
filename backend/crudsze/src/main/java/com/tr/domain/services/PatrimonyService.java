package com.tr.domain.services;

import com.tr.domain.entities.Patrimony;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.PatrimonyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatrimonyService {

  private static final String MSG_PATROMONY_NOT_FOUND =
      "There is no patrimony registration with the code %d";
  private static final String MSG_PATROMONY_IN_USE =
      "Code patrimony cannot be removed as it is in use";

  @Autowired private PatrimonyRepository repository;
  @Autowired private LedgerAccountService ledgerAccountService;

  @Transactional
  public Patrimony createPatrimony(final Patrimony patrimony) {
    return repository.save(patrimony);
  }

  @Transactional
  public Patrimony findPatrimonyById(final Long patrimonyId) {
    return repository
        .findById(patrimonyId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_PATROMONY_NOT_FOUND, patrimonyId)));
  }

  @Transactional
  public Patrimony updatePatrimony(final Patrimony patrimony) {
    var entity =
        repository
            .findById(patrimony.getPatrimonyId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setDescription(entity.getDescription());
    entity.setCode(entity.getCode());
    entity.setLocation(entity.getLocation());
    entity.setAccountingClassification(entity.getAccountingClassification());
    entity.setDateOfPurchase(entity.getDateOfPurchase());
    entity.setPurchaseInvoice(entity.getPurchaseInvoice());
    return repository.save(patrimony);
  }

  public void deletePatrimony(Long patrimonyId) {
    try {
      repository.deleteById(patrimonyId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_PATROMONY_NOT_FOUND, patrimonyId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_PATROMONY_IN_USE, patrimonyId));
    }
  }

  public List<Patrimony> findAllByPatrimony() {
    return repository.findAll();
  }
}
