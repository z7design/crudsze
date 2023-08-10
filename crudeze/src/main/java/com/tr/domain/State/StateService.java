package com.tr.domain.State;

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
public class StateService {
  private static final String MSG_STATE_NOT_FOUND =
      "There is no state registration with the code %d";
  private static final String MSG_STATE_IN_USE = "Code state %d cannot be removed as it is in use";

  @Autowired private StateRepository stateRepository;

  public StateEntity createState(final StateEntity state) {

    return stateRepository.save(state);
  }

  @Transactional
  public StateEntity updateState(final StateEntity state) {
    StateEntity entity =
        stateRepository
            .findById(state.getStateId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    entity.setUf(entity.getUf());

    return stateRepository.save(state);
  }

  @Transactional
  public List<StateEntity> findAllByStates() {
    return stateRepository.findAll();
  }

  @Transactional
  public StateEntity findStateById(final Long stateId) {
    return stateRepository
        .findById(stateId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, stateId)));
  }

  public void deleteState(Long stateId) {
    try {
      stateRepository.deleteById(stateId);

    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, stateId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_STATE_IN_USE, stateId));
    }
  }
}
