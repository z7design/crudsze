package com.tr.domain.services;

import com.tr.domain.entities.Launch;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.LaunchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LaunchService {
  private static final String MSG_LAUNCH_NOT_FOUND = "There is no launch registration with the code %d";
  private static final String MSG_LAUNCH_IN_USE = "Code launch %d cannot be removed as it is in use";

  @Autowired private LaunchRepository repository;
  @Autowired private AccountBankService accountService;

  @Transactional
  public Launch createLaunch(final Launch launch) {
    return repository.save(launch);
  }

  @Transactional
  public Launch findLaunchById(final Long launchId) {
    return repository
        .findById(launchId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_LAUNCH_NOT_FOUND, launchId)));
  }

  @Transactional
  public Launch updateLaunch(final Launch launch) {
    var entity =
        repository
            .findById(launch.getLaunchId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));
    
    entity.setTypeLaunch(entity.getTypeLaunch());
    entity.setTax(entity.getTax());
    entity.setValue(entity.getValue());

    return repository.save(launch);
  }
  
  public void deleteLaunch(Long launchId) {
    try {
      repository.deleteById(launchId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_LAUNCH_NOT_FOUND, launchId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_LAUNCH_IN_USE, launchId));
    }
  }

  public List<Launch> findAllByLaunch() {

    return repository.findAll();
  }
}
