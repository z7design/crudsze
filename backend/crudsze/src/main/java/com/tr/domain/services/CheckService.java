package com.tr.domain.services;

import com.tr.domain.entities.CheckBank;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.CheckRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckService {

  private static final String MSG_CHECK_NOT_FOUND =
      "There is no city registration with the code %d";
  private static final String MSG_CHECK_IN_USE = "Code city %d cannot be removed as it is in use";

  @Autowired private CheckRepository repository;
  @Autowired private CheckbookService checkbookService;

  @Transactional
  public CheckBank createCheck(final CheckBank check) {
    return repository.save(check);
  }

  @Transactional
  public CheckBank findCheckById(final Long checkId) {
    return repository
        .findById(checkId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_CHECK_NOT_FOUND, checkId)));
  }

  @Transactional
  public CheckBank updateChek(final CheckBank check) {
    CheckBank entity =
        repository
            .findById(check.getCheckId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setNumber(entity.getNumber());
    entity.setStatus(entity.getStatus());
    entity.setValue(entity.getValue());
    entity.setDateEmission(entity.getDateEmission());
    entity.setBomPara(entity.getBomPara());
    entity.setDateCompensation(entity.getDateCompensation());
    entity.setDateEmission(entity.getDateEmission());
    entity.setNominalTo(entity.getNominalTo());
    return repository.save(check);
  }
  
  public void deleteCheck(Long checkId) {
    try {
      repository.deleteById(checkId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_CHECK_NOT_FOUND, checkId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_CHECK_IN_USE, checkId));
    }
  }

  public List<CheckBank> findAllByCheck() {
    return repository.findAll();
  }
}
