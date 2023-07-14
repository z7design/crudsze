package com.tr.domain.services;

import com.tr.domain.entities.Installment;
import com.tr.domain.entities.Launch;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.InstallmentRepository;
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
public class InstallmentService {

  private static final String MSG_INSTALMENT_NOT_FOUND =
      "There is no city registration with the code %d";
  private static final String MSG_INSTALMENT_IN_USE =
      "Code city %d cannot be removed as it is in use";

  @Autowired private InstallmentRepository repository;
  @Autowired private LaunchService launchService;

  @Transactional
  public Installment createInstallment(final Installment installment) {
    Long lauchId = installment.getLaunch().getLaunchId();
    Launch launch = launchService.findLaunchById(lauchId);

    installment.setLaunch(launch);
    return repository.save(installment);
  }

  @Transactional
  public Installment findInstallmentById(final Long installmentId) {
    return repository
        .findById(installmentId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(MSG_INSTALMENT_NOT_FOUND, installmentId)));
  }

  @Transactional
  public Installment updateInstallment(final Installment installment) {
    var entity =
        repository
            .findById(installment.getInstallmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setDatePayment(entity.getDatePayment());
    entity.setValuePay(entity.getValuePay());
    entity.setDiscont(entity.getDiscont());
    entity.setInterest(entity.getInterest());
    entity.setAdditions(entity.getAdditions());
    entity.setFines(entity.getFines());

    return repository.save(installment);
  }

  @DeleteMapping("/{installmentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteInstallment(Long installmentId) {
    try {
      repository.deleteById(installmentId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_INSTALMENT_NOT_FOUND, installmentId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_INSTALMENT_IN_USE, installmentId));
    }
  }

  public List<Installment> findAllByInstallment() {
    return repository.findAll();
  }
}
