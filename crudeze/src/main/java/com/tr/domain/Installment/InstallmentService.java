package com.tr.domain.Installment;

import com.tr.domain.Launch.LaunchService;
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
public class InstallmentService {

  private static final String MSG_INSTALMENT_NOT_FOUND =
      "There is no city registration with the code %d";
  private static final String MSG_INSTALMENT_IN_USE =
      "Code city %d cannot be removed as it is in use";

  @Autowired private InstallmentRepository repository;
  @Autowired private LaunchService launchService;

  @Transactional
  public InstallmentEntity createInstallment(final InstallmentEntity installment) {
    return repository.save(installment);
  }

  @Transactional
  public InstallmentEntity findInstallmentById(final Long installmentId) {
    return repository
        .findById(installmentId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format(MSG_INSTALMENT_NOT_FOUND, installmentId)));
  }

  @Transactional
  public InstallmentEntity updateInstallment(final InstallmentEntity installment) {
    InstallmentEntity entity =
        repository
            .findById(installment.getInstallmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setDatePayment(entity.getDatePayment());
    entity.setValuePay(entity.getValuePay());
    entity.setDiscont(entity.getDiscont());
    entity.setAddtions(entity.getAddtions());
    entity.setFines(entity.getFines());

    return repository.save(installment);
  }

  public void deleteInstallment(Long installmentId) {
    try {
      repository.deleteById(installmentId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_INSTALMENT_NOT_FOUND, installmentId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_INSTALMENT_IN_USE, installmentId));
    }
  }

  public List<InstallmentEntity> findAllByInstallment() {
    return repository.findAll();
  }
}
