package com.tr.domain.services;

import com.tr.domain.entities.Checkbook;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.CheckbookRepository;
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
public class CheckbookService {

  private static final String MSG_CHECKBOOK_NOT_FOUND = "There is no checkbook with the code %d";
  private static final String MSG_CHECKBOOK_IN_USE = "Code checkbook %d cannot be removed as it is in use";

  @Autowired private CheckbookRepository repository;

  @Transactional
   public Checkbook createCheckbook(Checkbook checkbook) {
    return repository.save(checkbook);
  }

  @Transactional
  public Checkbook findCheckbookById(final Long checkbookId) {
    return repository
        .findById(checkbookId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_CHECKBOOK_NOT_FOUND, checkbookId)));
  }

  @Transactional
  public Checkbook updateChebook(final Checkbook checkbook) {
    var entity =
        repository
            .findById(checkbook.getCheckbookId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setNumberTalon(entity.getNumberTalon());
    entity.setStatus(entity.getStatus());
    entity.setEmissionDate(entity.getEmissionDate());
    return repository.save(checkbook);
  }

  @DeleteMapping("/{checkbookId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCheckbook(Long checkbookId) {
    try {
      repository.deleteById(checkbookId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_CHECKBOOK_NOT_FOUND, checkbookId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_CHECKBOOK_IN_USE, checkbookId));
    }
  }

  public List<Checkbook> findAllByCheckbook() {
    return repository.findAll();
  }
}
