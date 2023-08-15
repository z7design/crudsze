package com.tr.domain.State;

import com.tr.domain.Titles.TitlesEntity;
import com.tr.domain.Titles.TitlesRequestDTO;
import com.tr.domain.Titles.TitlesResponse;
import com.tr.domain.User.UserEntity;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StateService {
  private static final String MSG_STATE_NOT_FOUND =
      "There is no state registration with the code %d";
  private static final String MSG_STATE_IN_USE = "Code state %d cannot be removed as it is in use";

  @Autowired private StateRepository stateRepository;
  @Autowired private ModelMapper mapper;

  public StateResponse save(StateRequestDTO dto) {

    StateEntity state = mapper.map(dto, StateEntity.class);
    UserEntity user =
        (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    state.setUserEntity(user);
    state.setStateId(null);
    state = stateRepository.save(state);

    return mapper.map(state, StateResponse.class);
  }

  @Transactional
 public StateResponse update(Long stateId, StateRequestDTO dto) {
    findStateById(stateId);
    StateEntity stateEntity = mapper.map(dto, StateEntity.class);

    UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    stateEntity.setUserEntity(user);
    stateEntity.setStateId(null);
    stateEntity = stateRepository.save(stateEntity);

    return mapper.map(stateEntity, StateResponse.class);
  }


  public List<StateResponse> findAllByStates() {
    UserEntity user =
        (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<StateEntity> states = stateRepository.findByUserEntity(user);

    return states.stream()
        .map(state -> mapper.map(state, StateResponse.class))
        .collect(Collectors.toList());
  }


  public StateResponse findStateById(Long stateId) {
    Optional<StateEntity> optionalState = stateRepository.findById(stateId);
    UserEntity user =
        (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (optionalState.isPresent() || optionalState.get().getUserEntity().getUserId() != user.getUserId()) {
      throw new ResourceNotFoundException("Could not find state with id " + stateId);
    }
    return mapper.map(optionalState.get(), StateResponse.class);
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
