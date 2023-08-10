package com.tr.domain.Launch;

import com.tr.domain.AccountBank.AccountBankService;
import com.tr.domain.AccountPayble.AccountPaybleEntity;
import com.tr.domain.AccountPayble.AccountPaybleService;
import com.tr.domain.AccountReceive.AccountReceiveEntity;
import com.tr.domain.AccountReceive.AccountReceiveService;
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
public class LaunchService {
  private static final String MSG_LAUNCH_NOT_FOUND =
      "There is no LaunchEntity registration with the code %d";
  private static final String MSG_LAUNCH_IN_USE =
      "Code LaunchEntity %d cannot be removed as it is in use";

  @Autowired 
  private LaunchRepository repository;
  @Autowired 
  private AccountBankService accountService;
  @Autowired 
  private AccountPaybleService accountPaybleService;
  @Autowired 
  private AccountReceiveService accountReceiveService;

  @Transactional
  public LaunchEntity createLaunch(final LaunchEntity launchEntity) {
    Long accountPaybleId = launchEntity.getAccountPayble().getAccountPaybleId();
    AccountPaybleEntity accountPayble = accountPaybleService.findAccounPaybleById(accountPaybleId);

    Long accountReceiveId = launchEntity.getAccountReceive().getAccountReceiveId();
    AccountReceiveEntity accountReceive = accountReceiveService.findAccounReceiveById(accountReceiveId);

    launchEntity.setAccountPayble(accountPayble);
    launchEntity.setAccountReceive(accountReceive);

    return repository.save(launchEntity);
  }

  @Transactional
  public LaunchEntity findLaunchEntityById(final Long launchEntityId) {
    return repository
        .findById(launchEntityId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_LAUNCH_NOT_FOUND, launchEntityId)));
  }

  @Transactional
  public LaunchEntity updateLaunch(final LaunchEntity launchEntity) {
    LaunchEntity entity =
        repository
            .findById(launchEntity.getLaunchId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setTypeLaunch(entity.getTypeLaunch());
    entity.setTax(entity.getTax());
    entity.setValue(entity.getValue());

    return repository.save(launchEntity);
  }

  public void deleteLaunch(Long launchEntityId) {
    try {
      repository.deleteById(launchEntityId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_LAUNCH_NOT_FOUND, launchEntityId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_LAUNCH_IN_USE, launchEntityId));
    }
  }

  public List<LaunchEntity> findAllByLaunchEntity() {

    return repository.findAll();
  }
}
