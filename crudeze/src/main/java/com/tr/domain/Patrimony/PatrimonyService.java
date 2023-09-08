package com.tr.domain.Patrimony;

import com.tr.domain.LedgerAccount.LedgerAccountService;
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
public class PatrimonyService {

  private static final String MSG_PATROMONY_NOT_FOUND =
      "There is no patrimony registration with the code %d";
  private static final String MSG_PATROMONY_IN_USE =
      "Code patrimony cannot be removed as it is in use";

  @Autowired private PatrimonyRepository repository;
  @Autowired private LedgerAccountService ledgerAccountService;

  @Transactional
  public PatrimonyEntity createPatrimony(PatrimonyEntity patrimony) {
    return repository.save(patrimony);
  }

  @Transactional
  public PatrimonyEntity findPatrimonyById(final Long patrimonyId) {
    return repository
        .findById(patrimonyId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_PATROMONY_NOT_FOUND, patrimonyId)));
  }

  @Transactional
  public PatrimonyEntity updatePatrimony(final PatrimonyEntity patrimony) {
    PatrimonyEntity entity =
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

  public List<PatrimonyEntity> findAllByPatrimony() {
    
    return repository.findAll();
  }
}
