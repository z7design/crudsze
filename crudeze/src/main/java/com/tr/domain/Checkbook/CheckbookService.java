package com.tr.domain.Checkbook;

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
public class CheckbookService {

  private static final String MSG_CHECKBOOK_NOT_FOUND = "There is no checkbook with the code %d";
  private static final String MSG_CHECKBOOK_IN_USE = "Code checkbook %d cannot be removed as it is in use";

  @Autowired private CheckbookRepository repository;

  @Transactional
   public CheckbookEntity createCheckbook(CheckbookEntity checkbook) {
    return repository.save(checkbook);
  }

  @Transactional
  public CheckbookEntity findCheckbookById(final Long checkbookId) {
    return repository
        .findById(checkbookId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_CHECKBOOK_NOT_FOUND, checkbookId)));
  }

  @Transactional
  public CheckbookEntity updateChebook(final CheckbookEntity checkbook) {
    CheckbookEntity entity =
        repository
            .findById(checkbook.getCheckbookId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setNumberTalon(entity.getNumberTalon());
    entity.setStatus(entity.getStatus());
    entity.setEmissionDate(entity.getEmissionDate());
    return repository.save(checkbook);
  }
  
  public void deleteCheckbook(Long checkbookId) {
    try {
      repository.deleteById(checkbookId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_CHECKBOOK_NOT_FOUND, checkbookId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_CHECKBOOK_IN_USE, checkbookId));
    }
  }

  public List<CheckbookEntity> findAllByCheckbook() {
    return repository.findAll();
  }
}
