package com.tr.domain.services;

import com.tr.domain.entities.Departament;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.DepartamentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartamentService {
  private static final String MSG_DEPARTAMENT_NOT_FOUND =
      "There is no departament registration with the code %d";
  private static final String MSG_DEPARTAMENT_IN_USE = "Code departament %d cannot be removed as it is in use";

  @Autowired private DepartamentRepository departamentRepository;

  public Departament createDepartament(Departament departament) {
    return departamentRepository.save(departament);
  }

  @Transactional
  public Departament updateDepartament(final Departament departament) {
    var entity =
        departamentRepository
            .findById(departament.getDepartamentId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    entity.setLocaly(entity.getLocaly());
    entity.setDescription(entity.getDescription());

    return departamentRepository.save(departament);
  }

  @Transactional
  public List<Departament> findAllByDepartament() {
    return departamentRepository.findAll();
  }

  @Transactional
  public Departament findDepartamentById(final Long departamentId) {
    return departamentRepository
        .findById(departamentId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_DEPARTAMENT_NOT_FOUND, departamentId)));
  }

  public void deleteDepartament(Long departamentId) {
    try {
      departamentRepository.deleteById(departamentId);

    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_DEPARTAMENT_NOT_FOUND, departamentId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_DEPARTAMENT_IN_USE, departamentId));
    }
  }
}
