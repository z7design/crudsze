package com.tr.domain.services;

import com.tr.domain.entities.Veicle;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.VeicleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VeicleService {
  private static final String MSG_VEICLE_NOT_FOUND =
      "There is no veicle registration with the code %d";
  private static final String MSG_VEICLE_IN_USE =
      "Code veicle %d cannot be removed as it is in use";

  @Autowired private VeicleRepository repository;
  @Autowired private DocumentService documentService;

  @Transactional
  public Veicle createVeicle(Veicle veicle) {
    return repository.save(veicle);
  }

  @Transactional
  public Veicle findVeicleById(final Long veicleId) {
    return repository
        .findById(veicleId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_VEICLE_NOT_FOUND, veicleId)));
  }

  @Transactional
  public Veicle update(final Veicle veicle) {
    var entity =
        repository
            .findById(veicle.getVeicleId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setPlate(entity.getPlate());
    entity.setColor(entity.getColor());
    entity.setModel(entity.getModel());
    entity.setValue(entity.getValue());

    return repository.save(veicle);
  }

  public void deleteVeicle(Long veicleId) {
    try {
      repository.deleteById(veicleId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_VEICLE_NOT_FOUND, veicleId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_VEICLE_IN_USE, veicleId));
    }
  }

  @Transactional
  public List<Veicle> findAllByVeicle() {
    return repository.findAll();
  }
}
